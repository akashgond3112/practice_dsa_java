package main.interview.wise.circuitbreaker;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

class CircuitBreaker {
    private enum State {
        CLOSED, OPEN, HALF_OPEN
    }

    private final String serviceName;
    private final int errorThreshold; // Y errors
    private final long timeWindowMs; // in last X minutes
    private final long coolOffPeriodMs; // Z minutes cooloff

    private final AtomicReference<State> state = new AtomicReference<>(State.CLOSED);
    private final ConcurrentLinkedQueue<RequestRecord> requestHistory = new ConcurrentLinkedQueue<>();
    private final AtomicLong circuitOpenedAt = new AtomicLong(0);
    private final AtomicInteger halfOpenSuccessCount = new AtomicInteger(0);

    public CircuitBreaker(String serviceName, int errorThreshold, long timeWindowMs, long coolOffPeriodMs) {
        this.serviceName = serviceName;
        this.errorThreshold = errorThreshold;
        this.timeWindowMs = timeWindowMs;
        this.coolOffPeriodMs = coolOffPeriodMs;
    }

    // Main method to check if request should be allowed
    public boolean allowRequest() {
        cleanupOldRecords();

        State currentState = state.get();

        switch (currentState) {
            case OPEN:
                // Check if cool-off period has passed
                if (System.currentTimeMillis() - circuitOpenedAt.get() >= coolOffPeriodMs) {
                    // Try to move to HALF_OPEN
                    if (state.compareAndSet(State.OPEN, State.HALF_OPEN)) {
                        System.out.println("[" + serviceName + "] Circuit HALF_OPEN - Testing...");
                        halfOpenSuccessCount.set(0);
                        return true;
                    }
                }
                System.out.println("[" + serviceName + "] Circuit OPEN - Request blocked (failfast)");
                return false;

            case HALF_OPEN:
                // Allow limited requests to test if service recovered
                return true;

            case CLOSED:
            default:
                return true;
        }
    }

    // Record request result
    public void recordSuccess() {
        long now = System.currentTimeMillis();
        requestHistory.offer(new RequestRecord(now, true));

        State currentState = state.get();

        if (currentState == State.HALF_OPEN) {
            // If we get 3 successful requests in HALF_OPEN, close the circuit
            if (halfOpenSuccessCount.incrementAndGet() >= 3) {
                if (state.compareAndSet(State.HALF_OPEN, State.CLOSED)) {
                    System.out.println("[" + serviceName + "] Circuit CLOSED - Service recovered");
                    requestHistory.clear();
                }
            }
        }
    }

    public void recordFailure(int statusCode) {
        // Only count server errors (5xx) for circuit breaking
        if (statusCode < 500) {
            System.out.println("[" + serviceName + "] Client error " + statusCode + " - Not counted");
            return;
        }

        long now = System.currentTimeMillis();
        requestHistory.offer(new RequestRecord(now, false));

        State currentState = state.get();

        if (currentState == State.HALF_OPEN) {
            // Any failure in HALF_OPEN should open circuit again
            if (state.compareAndSet(State.HALF_OPEN, State.OPEN)) {
                circuitOpenedAt.set(System.currentTimeMillis());
                System.out.println("[" + serviceName + "] Circuit OPEN again - Service still failing");
            }
            return;
        }

        // Check if error threshold exceeded in time window
        int errorCount = countRecentErrors();
        System.out.println("[" + serviceName + "] Error count in last " +
                (timeWindowMs / 1000) + "s: " + errorCount + "/" + errorThreshold);

        if (errorCount >= errorThreshold) {
            // Trip the circuit
            if (state.compareAndSet(State.CLOSED, State.OPEN)) {
                circuitOpenedAt.set(System.currentTimeMillis());
                System.out.println("[" + serviceName + "] Circuit OPEN - Too many errors!");
            }
        }
    }

    // Count errors in the time window (thread-safe)
    private int countRecentErrors() {
        long cutoffTime = System.currentTimeMillis() - timeWindowMs;
        int count = 0;

        for (RequestRecord record : requestHistory) {
            if (record.timestamp >= cutoffTime && !record.success) {
                count++;
            }
        }
        return count;
    }

    // Clean up old records outside time window
    private void cleanupOldRecords() {
        long cutoffTime = System.currentTimeMillis() - timeWindowMs;

        // Remove old records from queue head
        while (!requestHistory.isEmpty()) {
            RequestRecord record = requestHistory.peek();
            if (record != null && record.timestamp < cutoffTime) {
                requestHistory.poll();
            } else {
                break;
            }
        }
    }

    public State getState() {
        return state.get();
    }

    public String getServiceName() {
        return serviceName;
    }

    // Immutable record for thread-safety
    private static class RequestRecord {
        final long timestamp;
        final boolean success;

        RequestRecord(long timestamp, boolean success) {
            this.timestamp = timestamp;
            this.success = success;
        }
    }
}
