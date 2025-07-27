package com.taskflow.domains.authentication.exceptionHandler;

import com.taskflow.domains.authentication.exception.*;
import com.taskflow.infra.DefaultErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestAuthExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidRegisterFieldsException.class)
    public ResponseEntity<DefaultErrorDTO> handleInvalidRegisterFieldsException(InvalidRegisterFieldsException ex) {
        DefaultErrorDTO error = new DefaultErrorDTO(
                HttpStatus.BAD_REQUEST,
                "AUTH-ERR-CODE-001",
                ex.getMessage(),
                "Invalid or null fields in register new user"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ShortRegisterPasswordException.class)
    public ResponseEntity<DefaultErrorDTO> handleShortRegisterPasswordException(ShortRegisterPasswordException ex) {
        DefaultErrorDTO error = new DefaultErrorDTO(
                HttpStatus.BAD_REQUEST,
                "AUTH-ERR-CODE-002",
                ex.getMessage(),
                "Password sent wass too short"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<DefaultErrorDTO> handleInvalidEmailException(InvalidEmailException ex) {
        DefaultErrorDTO error = new DefaultErrorDTO(
                HttpStatus.BAD_REQUEST,
                "AUTH-ERR-CODE-003",
                ex.getMessage(),
                "Email is not valid"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(FailedUserCreationException.class)
    public ResponseEntity<DefaultErrorDTO> handleFailedUserCreationException(FailedUserCreationException ex) {
        DefaultErrorDTO error = new DefaultErrorDTO(
                HttpStatus.BAD_REQUEST,
                "AUTH-ERR-CODE-004",
                ex.getMessage(),
                "User creation failed"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InvalidEmailOrPasswordException.class)
    public ResponseEntity<DefaultErrorDTO> handleInvalidEmailOrPasswordException(InvalidEmailOrPasswordException ex) {
        DefaultErrorDTO error = new DefaultErrorDTO(
                HttpStatus.UNAUTHORIZED,
                "AUTH-ERR-CODE-005",
                ex.getMessage(),
                "Invalid email or password"
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<DefaultErrorDTO> handleBadCredentials(BadCredentialsException ex) {
        DefaultErrorDTO error = new DefaultErrorDTO(
                HttpStatus.UNAUTHORIZED,
                "AUTH-ERR-INVALID-CREDENTIALS",
                "Invalid email or password",
                "Authentication failed"
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<DefaultErrorDTO> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        DefaultErrorDTO error = new DefaultErrorDTO(
                HttpStatus.NOT_FOUND,
                "AUTH-ERR-USER-NOT-FOUND",
                ex.getMessage(),
                "User not found"
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
