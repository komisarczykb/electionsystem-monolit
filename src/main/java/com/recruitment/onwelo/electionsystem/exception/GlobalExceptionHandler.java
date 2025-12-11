package com.recruitment.onwelo.electionsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            ElectionNotFoundException.class,
            VoterNotFoundException.class
    })
    public ResponseEntity<?> handleFindByIdNotInDatabase(Exception ex) {
        HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;
        ExceptionDetails details = new ExceptionDetails(ex.getMessage(), notFoundStatus);
        return ResponseEntity.status(notFoundStatus).body(details);
    }

    // Resolved [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot deserialize value of type `com.recruitment.onwelo.electionsystem.entity.enums.VoterStatus` from String "A": not one of the values accepted for Enum class: [BLOCKED, ACTIVE]]
    // @ExceptionHandler(HttpMessageNotReadableException.class)
}
