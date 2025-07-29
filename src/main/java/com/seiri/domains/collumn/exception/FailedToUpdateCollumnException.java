package com.seiri.domains.collumn.exception;

public class FailedToUpdateCollumnException extends RuntimeException {
    public FailedToUpdateCollumnException(String message) {
        super(message);
    }

    public FailedToUpdateCollumnException() {
        super("Failed to update collumn");
    }
}
