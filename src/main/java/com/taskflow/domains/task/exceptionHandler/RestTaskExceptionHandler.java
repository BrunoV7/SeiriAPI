package com.taskflow.domains.task.exceptionHandler;

import com.taskflow.domains.task.exception.FailedToCreateTaskException;
import com.taskflow.domains.task.exception.InvalidTaskCreationParameters;
import com.taskflow.domains.task.exception.NoTaskFoundException;
import com.taskflow.infra.DefaultErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestTaskExceptionHandler {

    @ExceptionHandler(NoTaskFoundException.class)
    public ResponseEntity<DefaultErrorDTO> handleNoTaskFoundException(NoTaskFoundException e) {
        DefaultErrorDTO error = new DefaultErrorDTO(HttpStatus.NOT_FOUND, "TASK_NOT_FOUND", e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(FailedToCreateTaskException.class)
    public ResponseEntity<DefaultErrorDTO> handleFailedToCreateTaskException(FailedToCreateTaskException e) {
        DefaultErrorDTO error = new DefaultErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, "TASK_CREATION_FAILED", e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(InvalidTaskCreationParameters.class)
    public ResponseEntity<DefaultErrorDTO> handleInvalidTaskCreationParameters(InvalidTaskCreationParameters e) {
        DefaultErrorDTO error = new DefaultErrorDTO(HttpStatus.BAD_REQUEST, "INVALID_TASK_PARAMETERS", e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

} 