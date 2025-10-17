package main.interview.wise.circuitbreaker;

class ApiResponse {
    final int statusCode;
    final String body;

    ApiResponse(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }
}
