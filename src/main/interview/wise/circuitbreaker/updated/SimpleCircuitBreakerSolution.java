package main.interview.wise.circuitbreaker.updated;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleCircuitBreakerSolution {

    // Simulation of a Request object
    static class Request {
        String serviceName;
        String payload;

        public Request(String serviceName, String payload) {
            this.serviceName = serviceName;
            this.payload = payload;
        }
    }

    // Response object with status code
    static class Response {
        int statusCode;
        String body;

        public Response(int statusCode, String body) {
            this.statusCode = statusCode;
            this.body = body;
        }

        public boolean isSuccess() {
            return statusCode >= 200 && statusCode < 300;
        }

        public boolean isServerError() {
            return statusCode >= 500;
        }
    }

    // Simplified Circuit Breaker Class (No HALF_OPEN state)
    static class CircuitBreaker {
        enum State {
            CLOSED, OPEN
        }

        private State state = State.CLOSED;
        private final int FAILURE_THRESHOLD = 3;
        private final long FAILURE_WINDOW_MILLIS = 10 * 60 * 1000; // 10 Minutes
        private final long COOLDOWN_MILLIS = 5 * 60 * 1000; // 5 Minutes

        // Sliding window to track when failures happened
        private final Deque<Long> failureTimestamps = new ArrayDeque<>();

        // Track when circuit was opened
        private long circuitOpenedTimestamp = -1;

        // Core logic to check if we can proceed
        public synchronized boolean allowRequest() {
            long now = System.currentTimeMillis();

            if (state == State.OPEN) {
                // Check if cooldown period has passed
                if (now - circuitOpenedTimestamp >= COOLDOWN_MILLIS) {
                    // Cooldown complete - close the circuit and reset
                    state = State.CLOSED;
                    failureTimestamps.clear();
                    circuitOpenedTimestamp = -1;
                    System.out.println("Circuit CLOSED after cooldown period");
                    return true;
                }
                // Still in cooldown period
                return false;
            }

            // Circuit is CLOSED, allow request
            return true;
        }

        public synchronized void recordSuccess() {
            // Success in CLOSED state - no action needed
            // Failures will naturally age out of the sliding window
        }

        public synchronized void recordFailure() {
            long now = System.currentTimeMillis();

            // Add new failure timestamp
            failureTimestamps.addLast(now);

            // Remove failures that are older than the failure window
            while (!failureTimestamps.isEmpty() &&
                    (now - failureTimestamps.peekFirst() > FAILURE_WINDOW_MILLIS)) {
                failureTimestamps.pollFirst();
            }

            // Check if we've hit the threshold
            if (failureTimestamps.size() >= FAILURE_THRESHOLD && state == State.CLOSED) {
                state = State.OPEN;
                circuitOpenedTimestamp = now; // Record when circuit opened
                System.out.println("Circuit OPENED at timestamp: " + circuitOpenedTimestamp);
            }
        }

        // Helper method to get current state (for debugging/monitoring)
        public synchronized State getState() {
            return state;
        }

        // Helper method to get remaining cooldown time
        public synchronized long getRemainingCooldownMillis() {
            if (state == State.OPEN && circuitOpenedTimestamp != -1) {
                long elapsed = System.currentTimeMillis() - circuitOpenedTimestamp;
                return Math.max(0, COOLDOWN_MILLIS - elapsed);
            }
            return 0;
        }
    }

    // The WebClient
    public static class WebClient {
        private final Map<String, CircuitBreaker> circuitBreakers = new ConcurrentHashMap<>();

        public Response execute(Request request) throws Exception {
            String service = request.serviceName;

            // Get or create the circuit breaker for this service
            CircuitBreaker cb = circuitBreakers.computeIfAbsent(service, k -> new CircuitBreaker());

            // Check if we are allowed to send request
            if (!cb.allowRequest()) {
                long remaining = cb.getRemainingCooldownMillis();
                System.out.println("BLOCKED: Circuit is OPEN for " + service +
                        " (cooldown remaining: " + (remaining / 1000) + "s)");
                throw new RuntimeException("Circuit Breaker Open");
            }

            // Attempt the actual network call
            Response response = sendExternalRequest(request);

            // Check response status code
            if (response.isSuccess()) {
                // Handle Success
                cb.recordSuccess();
                System.out.println("SUCCESS: Request to " + service +
                        " completed with status " + response.statusCode);
            } else if (response.isServerError()) {
                // Handle Server Error (5xx)
                cb.recordFailure();
                System.out.println("FAILURE: Request to " + service +
                        " failed with status " + response.statusCode);
            } else {
                // Client errors (4xx) don't count as circuit breaker failures
                System.out.println("CLIENT ERROR: Request to " + service +
                        " returned status " + response.statusCode);
            }

            return response;
        }

        // Mocking the external service call
        private Response sendExternalRequest(Request request) throws Exception {
            double random = Math.random();

            // Simulate different response scenarios
            if (random < 0.15) {
                // Network exception
                throw new Exception("Connection timeout");
            } else if (random < 0.30) {
                // 500 Internal Server Error
                return new Response(500, "Internal Server Error");
            } else if (random < 0.40) {
                // 503 Service Unavailable
                return new Response(503, "Service Unavailable");
            } else if (random < 0.50) {
                // 404 Not Found (client error - shouldn't trip circuit)
                return new Response(404, "Not Found");
            } else {
                // 200 OK
                return new Response(200, "Success");
            }
        }
    }

    // Main method to simulate the flow
    public static void main(String[] args) throws InterruptedException {
        WebClient client = new WebClient();
        Request reqB = new Request("Service-B", "Data");

        System.out.println("Starting Circuit Breaker simulation...\n");

        // Simulate multiple requests
        for (int i = 0; i < 20; i++) {
            System.out.println("--- Request #" + (i + 1) + " ---");
            try {
                Response response = client.execute(reqB);
                System.out.println("Response: " + response.statusCode + " - " + response.body);
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }
            Thread.sleep(500); // Wait 500ms between requests
            System.out.println();
        }
    }
}
