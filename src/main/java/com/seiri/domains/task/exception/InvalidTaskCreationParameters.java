package com.seiri.domains.task.exception;

public class InvalidTaskCreationParameters extends RuntimeException {
    public InvalidTaskCreationParameters(String message) {
        super(message);
    }

    public InvalidTaskCreationParameters(){
      super("Invalid task creation parameters");
    }
} 