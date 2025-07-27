package com.taskflow.domains.authentication.exception;

public class ShortRegisterPasswordException extends RuntimeException {

    public ShortRegisterPasswordException(String message) {
        super(message);
    }

    public ShortRegisterPasswordException() {
        super("Password is too short");
    }

}
