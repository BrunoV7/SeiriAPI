package com.taskflow.domains.auth.exception;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String message) {
        super(message);
    }

    public InvalidEmailException(){
        super("Invalid email");
    }
}
