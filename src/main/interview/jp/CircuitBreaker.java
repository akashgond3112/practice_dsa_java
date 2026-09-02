package main.interview.jp;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <h1>Circuit Breaker Pattern</h1>
 * The Circuit Breaker pattern is used to detect failures and encapsulate the logic of
 * preventing a failure from constantly recurring during maintenance or temporary outages.
 *
 * <b>Intuition:</b>
 * The state machine moves between:
 * <ul>
 *     <li><b>CLOSED:</b> System operates normally. Failures increment a counter.</li>
 *     <li><b>OPEN:</b> System is failing. Requests are rejected immediately (fail-fast)
 *     to prevent resource exhaustion.</li>
 *     <li><b>HALF_OPEN:</b> After a timeout, we allow a test request
 *     to verify if the downstream service has recovered.</li>
 * </ul>
 * This implementation uses atomic primitives to maintain thread safety without
 * blocking threads, ensuring high throughput.
 * Key Considerations for an Interviewer
 * Why not synchronized? It creates a performance bottleneck in high-load systems (every single request must enter a lock). Using AtomicReference and AtomicInteger allows lock-free performance while keeping the data consistent.
 * Memory Consistency: volatile on lastFailureTime is sufficient here. In the Java Memory Model, writes to volatile variables establish a "happens-before" relationship, ensuring every thread sees the most up-to-date timestamp immediately after it is set.
 * Edge Case - The Race for HALF_OPEN: The state.compareAndSet(State.OPEN, State.HALF_OPEN) is a "Compare-And-Swap" (CAS) operation. It ensures that even if 100 threads notice the timeout has expired, only one will successfully transition the state to HALF_OPEN, while the others will fail the CAS and correctly block.
 */
public class CircuitBreaker {

    public enum State {CLOSED, OPEN, HALF_OPEN}

    private final AtomicReference<State> state = new AtomicReference<>(State.CLOSED);
    private final AtomicInteger failureCount = new AtomicInteger(0);
    private volatile long lastFailureTime = 0;

    private final int failureThreshold;
    private final long resetTimeoutMs;

    public CircuitBreaker(int failureThreshold, long resetTimeoutMs) {
        this.failureThreshold = failureThreshold;
        this.resetTimeoutMs = resetTimeoutMs;
    }

    public boolean allowRequest() {
        if (state.get() == State.OPEN) {
            if (System.currentTimeMillis() - lastFailureTime >= resetTimeoutMs) {
                // Try to transition to HALF_OPEN
                return state.compareAndSet(State.OPEN, State.HALF_OPEN);
            }
            return false;
        }
        return true;
    }

    public void recordSuccess() {
        if (state.get() == State.HALF_OPEN) {
            state.set(State.CLOSED);
            failureCount.set(0);
        } else if (state.get() == State.CLOSED) {
            failureCount.set(0);
        }
    }

    public void recordFailure() {
        lastFailureTime = System.currentTimeMillis();

        if (state.get() == State.HALF_OPEN) {
            state.set(State.OPEN);
        } else if (state.get() == State.CLOSED) {
            if (failureCount.incrementAndGet() >= failureThreshold) {
                state.set(State.OPEN);
            }
        }
    }

    public State getState() {
        return state.get();
    }

    // --- MAIN TEST METHOD ---
    public static void main(String[] args) throws InterruptedException {
        CircuitBreaker cb = new CircuitBreaker(3, 1000);

        System.out.println("1. Normal Operation:");
        cb.recordSuccess();
        System.out.println("   Allow request: " + cb.allowRequest()); // true

        System.out.println("\n2. Tripping the Breaker:");
        cb.recordFailure();
        cb.recordFailure();
        cb.recordFailure();
        System.out.println("   Allow request: " + cb.allowRequest()); // false (OPEN)

        System.out.println("\n3. Fail-Fast (OPEN):");
        System.out.println("   Allow request: " + cb.allowRequest()); // false

        System.out.println("\n4. Recovery Window (HALF_OPEN):");
        Thread.sleep(1050);
        System.out.println("   Allow request (1.05s later): " + cb.allowRequest()); // true
        System.out.println("   Current state: " + cb.getState()); // HALF_OPEN

        System.out.println("\n5. Successful Recovery:");
        cb.recordSuccess();
        System.out.println("   Current state: " + cb.getState()); // CLOSED

        System.out.println("\n6. Failed Recovery:");
        cb.recordFailure();
        cb.recordFailure();
        cb.recordFailure();
        Thread.sleep(1050);
        cb.allowRequest(); // Now HALF_OPEN
        cb.recordFailure(); // Simulate test failure
        System.out.println("   After failed test, allow request: " + cb.allowRequest()); // false

        System.out.println("\n7. Concurrency Test:");
        CircuitBreaker cb2 = new CircuitBreaker(100, 1000);
        Runnable task = () -> {
            for (int i = 0; i < 50; i++) cb2.recordFailure();
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("   Allow request after 100 concurrent failures: " + cb2.allowRequest()); // false
    }


}