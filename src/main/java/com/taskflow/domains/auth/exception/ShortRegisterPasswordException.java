package com.taskflow.domains.auth.exception;

public class ShortRegisterPasswordException extends RuntimeException {

    public ShortRegisterPasswordException(String message) {
        super(message);
    }

    public ShortRegisterPasswordException() {
        super("Password is too short");
    }

}
