package com.seiri.domains.board.exceptionHandler;

import com.seiri.domains.board.exception.BoardNotFoundException;
import com.seiri.domains.board.exception.FailedToCreateBoardException;
import com.seiri.infra.DefaultErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestBoardExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BoardNotFoundException.class)
    public ResponseEntity<DefaultErrorDTO> handleBoardNotFoundException(BoardNotFoundException ex) {
        DefaultErrorDTO error = new DefaultErrorDTO(
                HttpStatus.BAD_REQUEST,
                "BOARD-ERR-CODE-001",
                ex.getMessage(),
                "Something went wrong while looking for the selected board"
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FailedToCreateBoardException.class)
    public ResponseEntity<DefaultErrorDTO> handleFailedToCreateBoardException(FailedToCreateBoardException ex) {
        DefaultErrorDTO error = new DefaultErrorDTO(
                HttpStatus.BAD_REQUEST,
                "BOARD-ERR-CODE-002",
                ex.getMessage(),
                "Something went wrong while creating the board"
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
