package com.emilmi.resume.exception;

import org.springframework.http.HttpStatus;

public class RestApiException extends RuntimeException {
    final HttpStatus statusCode;

    public RestApiException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
