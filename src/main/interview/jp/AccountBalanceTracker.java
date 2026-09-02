package main.interview.jp;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

public class AccountBalanceTracker {
    // Thread-safe map to store account balances for multiple users concurrently
    private final Map<String, Double> balances = new ConcurrentHashMap<>();

    /**
     * APPROACH 0 (In-Memory): Process a batch of transactions (representing lines
     * from a CSV file).
     * Loads all transactions into memory and processes them sequentially.
     * Format of each line: "customerId, amount"
     * Positive amount = deposit, Negative amount = withdrawal.
     * <p>
     * Time Complexity: O(n * m) where n = number of transactions, m = average
     * length of each line
     * (string parsing, splitting, and HashMap compute operation are O(1) amortized)
     * Space Complexity: O(u) where u = number of unique customers
     * (stores balance for each unique customer in ConcurrentHashMap)
     * <p>
     * Use case: Small to medium-sized transaction lists that fit in memory
     *
     * @param csvLines List of transaction strings to process
     */
    public void processTransactions(List<String> csvLines) {
        for (String line : csvLines) {
            String[] parts = line.split(",");
            if (parts.length != 2)
                continue;

            String customerId = parts[0].trim();
            double amount;

            try {
                amount = Double.parseDouble(parts[1].trim());
            } catch (NumberFormatException e) {
                System.err.println("Invalid amount format: " + parts[1]);
                continue; // Skip invalid records
            }

            // Thread-safe and atomic update of the balance
            balances.compute(customerId, (id, currentBalance) -> {
                if (currentBalance == null) {
                    currentBalance = 0.0;
                }

                if (amount < 0 && currentBalance < 0) {
                    // Rule: If user has a negative balance, skip the withdrawal
                    System.out.println("Skipping withdrawal of " + amount + " for " + customerId
                            + " due to existing negative balance.");
                    return currentBalance;
                }

                return currentBalance + amount;
            });
        }
    }

    /**
     * Retrieve the current balance for a specific customer.
     * <p>
     * Time Complexity: O(1) amortized - ConcurrentHashMap getOrDefault is O(1) on
     * average
     * Space Complexity: O(1) - constant space, no additional data structures
     * allocated
     *
     * @param customerId The ID of the customer
     * @return The balance of the customer, or 0.0 if customer not found
     */
    public double getBalance(String customerId) {
        return balances.getOrDefault(customerId, 0.0);
    }

    /**
     * APPROACH 1: Process transactions from a file using Java Streams
     * (memory-efficient).
     * Uses Files.lines() to read file line-by-line without loading entire file into
     * memory.
     * Suitable for very large CSV files that don't fit in system memory.
     * <p>
     * Time Complexity: O(n) where n = number of lines in file
     * Space Complexity: O(1) - constant space, doesn't buffer entire file
     *
     * @param filePath Path to the CSV file
     */
    public void processTransactionsFromFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (Files.notExists(path)) {
            System.out.println("File not found: " + filePath + ". Skipping file-based processing.");
            return;
        }

        try (Stream<String> lines = Files.lines(path)) {

            lines.skip(1) // LAZY: Tells the stream to ignore the first line without loading it
                    .filter(line -> !line.isBlank()) // Filter out empty lines lazily
                    .map(line -> line.split(","))
                    .filter(parts -> parts.length == 2)
                    .forEach(this::computeBalance);
        }
    }

    /**
     * APPROACH 2 (Alternative): Process transactions from file using
     * BufferedReader.
     * Lower memory footprint than Files.lines(), better for extremely large files.
     * Processes one transaction at a time.
     * <p>
     * Time Complexity: O(n) where n = number of lines in file
     * Space Complexity: O(1) - constant space
     *
     * @param filePath Path to the CSV file
     * @throws IOException if file cannot be read
     */
    public void processTransactionsFromFileBuffered(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (Files.notExists(path)) {
            System.err.println("File not found: " + filePath);
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            // 1. Pre-consume the header to simplify loop logic
            String header = reader.readLine();
            if (header == null) return; // Empty file

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;

                String[] parts = line.split(",");
                if (parts.length != 2) {
                    System.err.println("Skipping malformed line: " + line);
                    continue;
                }

                computeBalance(parts);
            }
        }
    }

    private void computeBalance(String[] parts) {
        String customerId = parts[0].trim();
        double amount;

        try {
            amount = Double.parseDouble(parts[1].trim());
        } catch (NumberFormatException e) {
            System.err.println("Invalid amount format: " + parts[1]);
            return;
        }

        // 2. Atomic update: No other thread can modify the balance
        // for this specific customerId while this block runs.
        balances.compute(customerId, (id, currentBalance) -> {
            double balance = (currentBalance == null) ? 0.0 : currentBalance;

            // Business Logic: Prevent withdrawal if already negative
            if (amount < 0 && balance < 0) {
                System.out.println("Skipping withdrawal of " + amount + " for " + id
                        + " due to existing negative balance.");
                return balance;
            }

            return balance + amount;
        });
    }

    /**
     * APPROACH 3: Process multiple CSV files concurrently using ExecutorService.
     * Each file is processed by a separate thread from the thread pool.
     * ConcurrentHashMap ensures thread-safe balance updates across threads.
     * <p>
     * Time Complexity: O(n/t) where n = total lines, t = number of threads
     * Space Complexity: O(t) where t = number of threads in pool
     *
     * @param filePaths  List of file paths to process concurrently
     * @param numThreads Number of threads in the thread pool
     * @throws InterruptedException if thread is interrupted while waiting
     */
    public void processTransactionsConcurrently(List<String> filePaths, int numThreads) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        CountDownLatch latch = new CountDownLatch(filePaths.size());

        for (String filePath : filePaths) {
            executorService.submit(() -> {
                try {
                    System.out
                            .println("[Thread-" + Thread.currentThread().threadId() + "] Processing file: " + filePath);
                    processTransactionsFromFile(filePath);
                    System.out
                            .println("[Thread-" + Thread.currentThread().threadId() + "] Completed file: " + filePath);
                } catch (IOException e) {
                    System.err.println("Error processing file " + filePath + ": " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        // Wait for all threads to complete
        latch.await();
        executorService.shutdown();
        System.out.println("All files processed successfully!");
    }

    /*
     * =============================================================================
     * ====
     * FOLLOW UP QUESTIONS & ANSWERS:
     *
     * Q1: How to handle too large a file to fit in system memory?
     * A: Instead of loading all lines into memory (like a List<String>), we should
     * read the file iteratively. We can use a `BufferedReader` (readLine()) or
     * Java Streams (`Files.lines(Path)`). This processes one line at a time,
     * keeping
     * memory usage low regardless of how large the CSV file is.
     *
     * Q2: How do you handle multiple files concurrently?
     * A: We can use a Thread Pool (`ExecutorService`). We submit each file parsing
     * task to a different thread. Because we use a `ConcurrentHashMap` and its
     * atomic `compute` method, multiple threads can safely update the balance of
     * the same customer concurrently without race conditions or overwriting data.
     * =============================================================================
     * ====
     */

    public static void main(String[] args) throws IOException, InterruptedException {
        AccountBalanceTracker tracker = new AccountBalanceTracker();

        System.out.println("========== APPROACH 0: In-Memory List Processing ==========");
        System.out.println("--- Test Case 1: Normal Deposits and Withdrawals ---");
        List<String> transactions1 = List.of(
                "user1, 100",
                "user1, -50",
                "user2, 200");
        tracker.processTransactions(transactions1);
        System.out.println("User1 balance: " + tracker.getBalance("user1") + " (Expected: 50.0)");
        System.out.println("User2 balance: " + tracker.getBalance("user2") + " (Expected: 200.0)");

        System.out.println("\n--- Test Case 2: Withdrawal with Negative Balance ---");
        List<String> transactions2 = List.of(
                "user3, 50",
                "user3, -100", // Balance becomes -50
                "user3, -20" // Should be skipped because balance is now negative (-50)
        );
        tracker.processTransactions(transactions2);
        System.out.println("User3 balance: " + tracker.getBalance("user3") + " (Expected: -50.0)");

        System.out.println("\n--- Test Case 3: Concurrent Updates Simulation ---");
        // Our 'compute' implementation guarantees thread safety in real concurrency
        List<String> transactions3 = List.of("user1, 50", "user2, -50");
        tracker.processTransactions(transactions3);
        System.out.println("User1 balance: " + tracker.getBalance("user1") + " (Expected: 100.0)");
        System.out.println("User2 balance: " + tracker.getBalance("user2") + " (Expected: 150.0)");

        System.out.println("\n--- Test Case 4: Invalid Formats ---");
        tracker.processTransactions(List.of("user4, invalid", "user5")); // Should ignore safely

        System.out.println("\n========== APPROACH 1: Stream-Based File Processing (Memory Efficient) ==========");
        System.out.println("--- Advantage: Handles large files without loading entire content into memory ---");

        var transactionsDir = Paths.get("src/main/interview/jp");
        Files.createDirectories(transactionsDir);

        var normalFile = transactionsDir.resolve("file_normal.csv");
        Files.writeString(normalFile,
                String.join(System.lineSeparator(),
                        "user1, 100",
                        "user1, -50",
                        "user2, 200"));

        var negativeBalanceFile = transactionsDir.resolve("file_negative.csv");
        Files.writeString(negativeBalanceFile,
                String.join(System.lineSeparator(),
                        "user3, 50",
                        "user3, -100",
                        "user3, -20"));

        var invalidFile = transactionsDir.resolve("file_invalid.csv");
        Files.writeString(invalidFile,
                String.join(System.lineSeparator(),
                        "user4, invalid",
                        "user5",
                        "user6, 25"));

        System.out.println("Processing demo CSV files from: " + transactionsDir);
        System.out.println("To test with real files, create CSV files and call:");
        System.out.println("  tracker.processTransactionsFromFile(\"path/to/file.csv\");");
        System.out.println("  OR tracker.processTransactionsFromFileBuffered(\"path/to/file.csv\");");

        AccountBalanceTracker normalFileTracker = new AccountBalanceTracker();
        System.out.println("\n--- Test Case 1: Normal Deposits and Withdrawals ---");
        normalFileTracker.processTransactionsFromFile(normalFile.toString());
        System.out.println("User1 balance: " + normalFileTracker.getBalance("user1") + " (Expected: 50.0)");
        System.out.println("User2 balance: " + normalFileTracker.getBalance("user2") + " (Expected: 200.0)");

        AccountBalanceTracker negativeBalanceTracker = new AccountBalanceTracker();
        System.out.println("\n--- Test Case 2: Withdrawal with Negative Balance ---");
        negativeBalanceTracker.processTransactionsFromFile(negativeBalanceFile.toString());
        System.out.println("User3 balance: " + negativeBalanceTracker.getBalance("user3") + " (Expected: -50.0)");

        AccountBalanceTracker invalidTracker = new AccountBalanceTracker();
        System.out.println("\n--- Test Case 3: Invalid Formats ---");
        invalidTracker.processTransactionsFromFile(invalidFile.toString());
        System.out.println("User4 balance: " + invalidTracker.getBalance("user4") + " (Expected: 0.0)");
        System.out.println("User5 balance: " + invalidTracker.getBalance("user5") + " (Expected: 0.0)");
        System.out.println("User6 balance: " + invalidTracker.getBalance("user6") + " (Expected: 25.0)");

        AccountBalanceTracker bufferedTracker = new AccountBalanceTracker();
        System.out.println("\n--- Test Case 4: BufferedReader File Processing ---");
        bufferedTracker.processTransactionsFromFileBuffered(normalFile.toString());
        System.out.println("User1 balance: " + bufferedTracker.getBalance("user1") + " (Expected: 50.0)");
        System.out.println("User2 balance: " + bufferedTracker.getBalance("user2") + " (Expected: 200.0)");

        System.out
                .println("\n========== APPROACH 2: Multi-Threading Test - Same File Processed Concurrently ==========");
        System.out.println("--- Test Case 7: 2 Threads Processing Same File Simultaneously ---");
        System.out.println("--- Demonstrates: Thread safety when multiple threads access the same file ---");
        AccountBalanceTracker concurrentTracker = new AccountBalanceTracker();
        CountDownLatch latch = new CountDownLatch(2);

        // Thread 1: Processes the normalFile
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("\n[Thread 1] Starting to process: " + normalFile.toString());
                concurrentTracker.processTransactionsFromFile(normalFile.toString());
                System.out.println("[Thread 1] Completed processing");
            } catch (IOException e) {
                System.err.println("[Thread 1] Error: " + e.getMessage());
            } finally {
                latch.countDown();
            }
        }, "Thread-1");

        // Thread 2: Processes the SAME normalFile
        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("\n[Thread 2] Starting to process: " + normalFile.toString());
                concurrentTracker.processTransactionsFromFileBuffered(normalFile.toString());
                System.out.println("[Thread 2] Completed processing");
            } catch (IOException e) {
                System.err.println("[Thread 2] Error: " + e.getMessage());
            } finally {
                latch.countDown();
            }
        }, "Thread-2");

        // Start both threads simultaneously
        thread1.start();
        thread2.start();

        // Wait for both threads to complete
        try {
            latch.await();
            System.out.println("\n[Main] Both threads completed!");
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted: " + e.getMessage());
        }

        // Verify consolidated results
        System.out.println("\n--- Consolidated Results (Both threads processed same file) ---");
        System.out.println("User1 balance: " + concurrentTracker.getBalance("user1") + " (Expected: 100.0)");
        System.out.println("User2 balance: " + concurrentTracker.getBalance("user2") + " (Expected: 400.0)");
        System.out.println("\nExplanation:");
        System.out.println("  - Each thread reads the same file: user1=100, user1=-50, user2=200");
        System.out.println("  - Thread 1 updates: user1=50, user2=200");
        System.out.println("  - Thread 2 updates: user1=50, user2=200 (on same shared map)");
        System.out.println("  - Final result: user1=100 (50+50), user2=400 (200+200)");
        System.out.println("  - ConcurrentHashMap ensures all updates are atomic and thread-safe");

    }
}
