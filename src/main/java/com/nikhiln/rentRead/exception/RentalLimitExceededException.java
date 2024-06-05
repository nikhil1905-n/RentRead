package com.nikhiln.rentRead.exception;

public class RentalLimitExceededException extends RuntimeException {

    private static final String DEFAULT_MSG = "Rental Limit Exceeded";

    public RentalLimitExceededException() {
        super(DEFAULT_MSG);
    }

    public RentalLimitExceededException(String message) {
        super(message);
    }
    
}
