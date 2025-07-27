package com.taskflow.domains.task.exception;

public class FailedToCreateTaskException extends RuntimeException {
    public FailedToCreateTaskException(String message) {
        super(message);
    }

    public FailedToCreateTaskException(){
      super("Failed to create task");
    }
} 