package com.example.bootcampisservice.exception;

public class IdentityServiceException extends RuntimeException{
    public IdentityServiceException() {
    }

    public IdentityServiceException(String message) {
        super(message);
    }

    public IdentityServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdentityServiceException(Throwable cause) {
        super(cause);
    }

    public IdentityServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
