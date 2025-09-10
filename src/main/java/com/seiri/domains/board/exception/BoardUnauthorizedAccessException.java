package com.seiri.domains.board.exception;

public class BoardUnauthorizedAccessException extends RuntimeException {
    public BoardUnauthorizedAccessException(String message) {
        super(message);
    }

    public BoardUnauthorizedAccessException() {
        super("You are not authorized to access this board");
    }
}
