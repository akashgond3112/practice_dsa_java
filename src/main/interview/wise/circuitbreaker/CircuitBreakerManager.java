package main.interview.wise.circuitbreaker;

import java.util.concurrent.*;

class CircuitBreakerManager {
    private final ConcurrentHashMap<String, CircuitBreaker> breakers = new ConcurrentHashMap<>();

    public CircuitBreaker getOrCreate(String serviceName, int errorThreshold,
            long timeWindowMs, long coolOffMs) {
        return breakers.computeIfAbsent(serviceName,
                k -> new CircuitBreaker(serviceName, errorThreshold, timeWindowMs, coolOffMs));
    }

    public CircuitBreaker get(String serviceName) {
        return breakers.get(serviceName);
    }
}
