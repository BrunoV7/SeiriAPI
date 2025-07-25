package com.taskflow.domains.auth.exception;

public class FailedUserCreationException extends RuntimeException {
    public FailedUserCreationException(String message) {
        super(message);
    }

    public FailedUserCreationException(){
        super("User creation failed");
    }
}
