package com.taskflow.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class DefaultRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<DefaultErrorDTO> handleRuntimeException(RuntimeException ex) {
        DefaultErrorDTO error = new DefaultErrorDTO(
                HttpStatus.BAD_REQUEST,
                "DEFAULT-ERR-CODE-001",
                ex.getMessage(),
                "Something went wrong!"
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
