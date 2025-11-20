package main.interview.wise.circuitbreaker.updated;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CircuitBreakerSolution {

    // Simulation of a Request object
    static class Request {
        String serviceName;
        String payload;

        public Request(String serviceName, String payload) {
            this.serviceName = serviceName;
            this.payload = payload;
        }
    }

    // The Core Circuit Breaker Class
    static class CircuitBreaker {
        enum State {
            CLOSED, OPEN, HALF_OPEN
        }

        private State state = State.CLOSED;
        private final int FAILURE_THRESHOLD = 3;
        private final long FAILURE_WINDOW_MILLIS = 10 * 60 * 1000; // 10 Minutes
        private final long COOLDOWN_MILLIS = 5 * 60 * 1000; // 5 Minutes

        // Sliding window to track when failures happened
        private final Deque<Long> failureTimestamps = new ArrayDeque<>();
        private long lastOpenTimestamp = -1;

        // Core logic to check if we can proceed
        public synchronized boolean allowRequest() {
            long now = System.currentTimeMillis();

            if (state == State.OPEN) {
                // Check if cooldown period has passed
                if (now - lastOpenTimestamp >= COOLDOWN_MILLIS) {
                    state = State.HALF_OPEN; // Allow one trial request
                    return true;
                }
                return false; // Still open, block request
            }

            // If CLOSED or HALF_OPEN, allow
            return true;
        }

        public synchronized void recordSuccess() {
            // If we were testing the waters (HALF_OPEN), we are now safe
            if (state == State.HALF_OPEN) {
                state = State.CLOSED;
                failureTimestamps.clear(); // Reset history on recovery
            }
            // If CLOSED, we just keep going, no need to clear history usually,
            // but dependent on specific requirements.
        }

        public synchronized void recordFailure() {
            long now = System.currentTimeMillis();

            if (state == State.HALF_OPEN) {
                // Failed immediately after cooldown -> Go back to OPEN
                state = State.OPEN;
                lastOpenTimestamp = now;
                return;
            }

            // Add new failure timestamp
            failureTimestamps.addLast(now);

            // Remove failures that are older than the 10-minute window
            while (!failureTimestamps.isEmpty() &&
                    (now - failureTimestamps.peekFirst() > FAILURE_WINDOW_MILLIS)) {
                failureTimestamps.pollFirst();
            }

            // Check threshold
            if (failureTimestamps.size() >= FAILURE_THRESHOLD) {
                state = State.OPEN;
                lastOpenTimestamp = now;
            }
        }
    }

    // The WebClient (The Interview entry point)
    public static class WebClient {
        // Registry to hold a Circuit Breaker for each service (B, C, etc.)
        private final Map<String, CircuitBreaker> circuitBreakers = new ConcurrentHashMap<>();

        public void execute(Request request) throws Exception {
            String service = request.serviceName;

            // Get or create the circuit breaker for this service
            CircuitBreaker cb = circuitBreakers.computeIfAbsent(service, k -> new CircuitBreaker());

            // 1. Check if we are allowed to send request
            if (!cb.allowRequest()) {
                System.out.println("BLOCKED: Circuit is OPEN for " + service);
                throw new RuntimeException("Circuit Breaker Open");
            }

            try {
                // 2. Attempt the actual network call
                sendExternalRequest(request);

                // 3. Handle Success
                cb.recordSuccess();
                System.out.println("SUCCESS: Request to " + service + " completed.");

            } catch (Exception e) {
                // 4. Handle Failure
                cb.recordFailure();
                System.out.println("FAILURE: Error talking to " + service);
                throw e; // Re-throw to caller
            }
        }

        // Mocking the external service call
        private void sendExternalRequest(Request request) throws Exception {
            // Simulate a random failure for demonstration
            if (Math.random() < 0.3) { // 30% chance of failure
                throw new Exception("500 Internal Server Error");
            }
        }
    }

    // Main method to simulate the flow
    public static void main(String[] args) throws InterruptedException {
        WebClient client = new WebClient();
        Request reqB = new Request("Service-B", "Data");

        // Simulate loop
        for (int i = 0; i < 10; i++) {
            try {
                client.execute(reqB);
            } catch (Exception e) {
                // Ignored for simulation loop
            }
            Thread.sleep(100);
        }
    }
}
