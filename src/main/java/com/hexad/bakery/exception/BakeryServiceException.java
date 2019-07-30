package com.hexad.bakery.exception;

public class BakeryServiceException extends RuntimeException {

    public BakeryServiceException(String message) {
        super(message);
    }

    public BakeryServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
