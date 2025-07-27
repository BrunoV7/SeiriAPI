package com.taskflow.domains.collumn.exception;

public class NoCollumnFoundException extends RuntimeException {
    public NoCollumnFoundException(String message) {
        super(message);
    }

    public NoCollumnFoundException() {
        super("No collumn found with the selected id");
    }
}
