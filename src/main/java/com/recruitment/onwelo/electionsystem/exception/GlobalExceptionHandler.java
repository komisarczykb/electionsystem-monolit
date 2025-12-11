package com.recruitment.onwelo.electionsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VoterNotFoundException.class)
    public ResponseEntity<?> handleVoterNotFoundException(VoterNotFoundException ex) {
        ExceptionDetails details = new ExceptionDetails(ex.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(details);
    }

    // Resolved [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot deserialize value of type `com.recruitment.onwelo.electionsystem.utils.VoterStatus` from String "A": not one of the values accepted for Enum class: [BLOCKED, ACTIVE]]
    // @ExceptionHandler(HttpMessageNotReadableException.class)
}
