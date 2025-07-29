package com.seiri.domains.task.exception;

public class NoTaskFoundException extends RuntimeException {
    public NoTaskFoundException(String message) {
        super(message);
    }

    public NoTaskFoundException(){
      super("No task found with the selected Id");
    }
} 