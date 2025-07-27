package com.taskflow.domains.cards.exceptionHandler;

import com.taskflow.domains.cards.exception.FailedToCreateCardException;
import com.taskflow.domains.cards.exception.InvalidCardCreationParameters;
import com.taskflow.domains.cards.exception.NoCardFoundException;
import com.taskflow.infra.DefaultErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestCardExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FailedToCreateCardException.class)
    public ResponseEntity<DefaultErrorDTO> handleFailedToCreateCardException(FailedToCreateCardException ex) {
        DefaultErrorDTO error = new DefaultErrorDTO(
                HttpStatus.BAD_REQUEST,
                "CARD-ERROR-CODE-001",
                ex.getMessage(),
                "Failed to create card!"
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCardCreationParameters.class)
    public ResponseEntity<DefaultErrorDTO> handleInvalidCardCreationParameters(InvalidCardCreationParameters ex) {
        DefaultErrorDTO error = new DefaultErrorDTO(
                HttpStatus.BAD_REQUEST,
                "CARD-ERROR-CODE-002",
                ex.getMessage(),
                "Invalid card creation parameters!"
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoCardFoundException.class)
    public ResponseEntity<DefaultErrorDTO> handleNoCardFoundException(NoCardFoundException ex) {
        DefaultErrorDTO error = new DefaultErrorDTO(
                HttpStatus.BAD_REQUEST,
                "CARD-ERROR-CODE-003",
                ex.getMessage(),
                "No card found!"
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}
