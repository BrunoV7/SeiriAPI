package com.taskflow.domains.auth.exception;

public class InvalidEmailOrPasswordException extends RuntimeException {
    public InvalidEmailOrPasswordException(String message) {
        super(message);
    }

    public InvalidEmailOrPasswordException(){
      super("Invalid email or password");
    }
}
