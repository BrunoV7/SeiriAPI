package com.seiri.domains.board.exception;

public class FailedToCreateBoardException extends RuntimeException {
    public FailedToCreateBoardException(String message) {
        super(message);
    }

    public FailedToCreateBoardException(){
        super("Something went wrong, Board could not be created");
    }
}
