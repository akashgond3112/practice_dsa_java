package main.interview.wise.circuitbreaker;

class CircuitBreakerOpenException extends Exception {
    CircuitBreakerOpenException(String message) {
        super(message);
    }
}
