package com.seiri.domains.authentication.exception;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String message) {
        super(message);
    }

    public InvalidEmailException(){
        super("Invalid email");
    }
}
