package com.seiri.domains.collumn.exceptionHandler;

import com.seiri.domains.collumn.exception.FailedToCreateCollumnException;
import com.seiri.domains.collumn.exception.FailedToUpdateCollumnException;
import com.seiri.domains.collumn.exception.NoCollumnFoundException;
import com.seiri.infra.DefaultErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestCollumnExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FailedToUpdateCollumnException.class)
    public ResponseEntity<DefaultErrorDTO> failedToUpdateCollumnException(FailedToUpdateCollumnException ex) {
        DefaultErrorDTO error = new DefaultErrorDTO(
                HttpStatus.BAD_REQUEST,
                "COL-ERROR-CODE-001",
                ex.getMessage(),
                "Failed to update the column. Please check your request and try again."
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FailedToCreateCollumnException.class)
    public ResponseEntity<DefaultErrorDTO> failedToCreateCollumnException(FailedToCreateCollumnException ex) {
        DefaultErrorDTO error = new DefaultErrorDTO(
                HttpStatus.BAD_REQUEST,
                "COL-ERROR-CODE-002",
                ex.getMessage(),
                "Failed to create the column. Please verify the input data and try again."
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoCollumnFoundException.class)
    public ResponseEntity<DefaultErrorDTO> noCollumnFoundException(NoCollumnFoundException ex) {
        DefaultErrorDTO error = new DefaultErrorDTO(
                HttpStatus.BAD_REQUEST,
                "COL-ERROR-CODE-003",
                ex.getMessage(),
                "No column found with the specified identifier."
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
