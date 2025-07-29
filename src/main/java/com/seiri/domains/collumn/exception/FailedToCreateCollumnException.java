package com.seiri.domains.collumn.exception;

public class FailedToCreateCollumnException extends RuntimeException {
    public FailedToCreateCollumnException(String message) {
        super(message);
    }

    public FailedToCreateCollumnException(){
        super("Something went wrong while creating a collumn.");
    }

}
