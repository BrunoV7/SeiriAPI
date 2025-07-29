package com.seiri.domains.board.exception;

public class BoardNotFoundException extends RuntimeException {
    public BoardNotFoundException(String message) {
        super(message);
    }

    public BoardNotFoundException(){
        super("No board with the selected id found");
    }
}
