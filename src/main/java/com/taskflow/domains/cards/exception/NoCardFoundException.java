package com.taskflow.domains.cards.exception;

public class NoCardFoundException extends RuntimeException {
    public NoCardFoundException(String message) {
        super(message);
    }

    public NoCardFoundException(){
      super("No card found with the selected Id");
    }
}
