package main.interview.wise.circuitbreaker;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class CircuitBreakerDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("=== Circuit Breaker Demo ===\n");

        // Create circuit breaker: 4 errors in 60 seconds -> open for 10 seconds
        CircuitBreaker breaker = new CircuitBreaker(
                "PaymentService",
                4, // errorThreshold
                60_000, // 60 seconds time window
                10_000 // 10 seconds cooloff
        );

        ApiClient client = new ApiClient("https://payment-service/api", breaker);

        System.out.println("--- Scenario 1: Normal operation ---");
        makeRequests(client, 3, false);
        Thread.sleep(500);

        System.out.println("\n--- Scenario 2: Service degradation (multiple failures) ---");
        // Simulate 5 failures to trip circuit breaker
        for (int i = 0; i < 8; i++) {
            try {
                simulateFailingRequest(client);
            } catch (Exception e) {
                // Expected
            }
            Thread.sleep(200);
        }

        System.out.println("\n--- Scenario 3: Circuit OPEN - Requests fail fast ---");
        makeRequests(client, 3, true);

        System.out.println("\n--- Scenario 4: Waiting for cooloff period... ---");
        Thread.sleep(10_000);

        System.out.println("\n--- Scenario 5: HALF_OPEN - Testing service recovery ---");
        makeRequests(client, 4, false);

        System.out.println("\n--- Scenario 6: Multi-threaded load test ---");
        testMultiThreaded(client);

        System.out.println("\n=== Demo Complete ===");
    }

    private static void makeRequests(ApiClient client, int count, boolean expectFailFast) {
        for (int i = 0; i < count; i++) {
            try {
                ApiResponse response = client.call();
                System.out.println("✓ Request " + (i + 1) + ": " + response.statusCode + " - " + response.body);
            } catch (CircuitBreakerOpenException e) {
                System.out.println("✗ Request " + (i + 1) + " blocked: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("✗ Request " + (i + 1) + " failed: " + e.getMessage());
            }
        }
    }

    private static void simulateFailingRequest(ApiClient client) throws Exception {
        // Force a failure by recording it directly
        CircuitBreaker breaker = new CircuitBreaker("test", 4, 60000, 10000);
        breaker.recordFailure(503);
        throw new Exception("Service unavailable");
    }

    private static void testMultiThreaded(ApiClient client) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(20);
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failureCount = new AtomicInteger(0);

        for (int i = 0; i < 20; i++) {
            final int requestId = i;
            executor.submit(() -> {
                try {
                    ApiResponse response = client.call();
                    successCount.incrementAndGet();
                    System.out.println("Thread-" + requestId + ": Success " + response.statusCode);
                } catch (CircuitBreakerOpenException e) {
                    failureCount.incrementAndGet();
                    System.out.println("Thread-" + requestId + ": Blocked by circuit breaker");
                } catch (Exception e) {
                    failureCount.incrementAndGet();
                    System.out.println("Thread-" + requestId + ": Failed");
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();

        System.out.println("\nMulti-threaded results: " +
                successCount.get() + " success, " +
                failureCount.get() + " failures");
    }
}
