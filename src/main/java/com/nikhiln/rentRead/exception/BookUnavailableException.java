package com.nikhiln.rentRead.exception;

public class BookUnavailableException extends RuntimeException {

    private static final String DEFAULT_MSG = "Book Unavailable";

    public BookUnavailableException() {
        super(DEFAULT_MSG);
    }

    public BookUnavailableException(String message) {
        super(message);
    }
    
}
