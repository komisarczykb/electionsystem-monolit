package me.bartoszkomisarczyk.electionsystem.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ExceptionDetails(String message, LocalDateTime timestamp, HttpStatus httpStatus) {
    public ExceptionDetails(String message, HttpStatus status) {
        this(message, LocalDateTime.now(), status);
    }
}
