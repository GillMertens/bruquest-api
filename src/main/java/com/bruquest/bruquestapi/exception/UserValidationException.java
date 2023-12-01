package com.bruquest.bruquestapi.exception;

import org.springframework.http.HttpStatus;

public class UserValidationException extends RuntimeException {
    private final HttpStatus httpStatus;
    public UserValidationException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
