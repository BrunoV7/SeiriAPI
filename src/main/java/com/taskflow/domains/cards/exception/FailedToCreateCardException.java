package com.taskflow.domains.cards.exception;

public class FailedToCreateCardException extends RuntimeException {
    public FailedToCreateCardException(String message) {
        super(message);
    }

    public FailedToCreateCardException(){
      super("Failed to create card");
    }
}
