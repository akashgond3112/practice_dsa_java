package main.interview.wise.circuitbreaker;

class ApiClient {
    private final CircuitBreaker circuitBreaker;
    private final String apiUrl;

    public ApiClient(String apiUrl, CircuitBreaker circuitBreaker) {
        this.apiUrl = apiUrl;
        this.circuitBreaker = circuitBreaker;
    }

    public ApiResponse call() throws Exception {
        // Check circuit breaker first
        if (!circuitBreaker.allowRequest()) {
            throw new CircuitBreakerOpenException(
                    "Circuit breaker is OPEN for " + circuitBreaker.getServiceName() +
                            ". Service temporarily unavailable. Please try again later.");
        }

        try {
            // Simulate actual API call
            ApiResponse response = makeHttpCall();

            if (response.statusCode >= 500) {
                circuitBreaker.recordFailure(response.statusCode);
            } else if (response.statusCode >= 400) {
                // Client error - don't trigger circuit breaker
                circuitBreaker.recordSuccess(); // Not a server issue
            } else {
                circuitBreaker.recordSuccess();
            }

            return response;

        } catch (Exception e) {
            // Network failure or timeout
            circuitBreaker.recordFailure(503);
            throw e;
        }
    }

    // Simulate HTTP call (replace with actual HTTP client in production)
    private ApiResponse makeHttpCall() throws Exception {
        // Simulate network delay
        Thread.sleep(50);

        // Simulate random failures for demo
        double random = Math.random();
        if (random < 0.3) {
            return new ApiResponse(503, "Service Unavailable");
        } else if (random < 0.4) {
            return new ApiResponse(401, "Unauthorized");
        } else {
            return new ApiResponse(200, "Success");
        }
    }
}
