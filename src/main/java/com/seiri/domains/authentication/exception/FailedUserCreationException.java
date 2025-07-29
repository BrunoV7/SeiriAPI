package com.seiri.domains.authentication.exception;

public class FailedUserCreationException extends RuntimeException {
    public FailedUserCreationException(String message) {
        super(message);
    }

    public FailedUserCreationException(){
        super("User creation failed");
    }
}
