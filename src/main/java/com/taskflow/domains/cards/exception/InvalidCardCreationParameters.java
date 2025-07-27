package com.taskflow.domains.cards.exception;

public class InvalidCardCreationParameters extends RuntimeException {
    public InvalidCardCreationParameters(String message) {
        super(message);
    }

    public InvalidCardCreationParameters(){
        super("Invalid Card Creation");
    }
}
