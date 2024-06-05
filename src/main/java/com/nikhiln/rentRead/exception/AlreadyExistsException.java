package com.nikhiln.rentRead.exception;

public class AlreadyExistsException extends RuntimeException {

    private static final String DEFAULT_MSG = "Resource Already Exists";

    public AlreadyExistsException() {
        super(DEFAULT_MSG);
    }

    public AlreadyExistsException(String message) {
        super(message);
    }
    
}
