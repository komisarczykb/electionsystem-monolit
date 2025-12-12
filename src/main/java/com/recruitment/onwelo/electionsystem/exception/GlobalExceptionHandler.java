package com.recruitment.onwelo.electionsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            ElectionNotFoundException.class,
            VoterNotFoundException.class,
            ElectionOptionNotFoundException.class
    })
    public ResponseEntity<?> handleNotFound(Exception ex) {
        HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;
        ExceptionDetails details = new ExceptionDetails(ex.getMessage(), notFoundStatus);
        return ResponseEntity.status(notFoundStatus).body(details);
    }

    @ExceptionHandler(AlreadyVotedException.class)
    public ResponseEntity<?> handleAlreadyVoted(AlreadyVotedException ex) {
        HttpStatus conflictStatus = HttpStatus.CONFLICT;
        ExceptionDetails details = new ExceptionDetails(ex.getMessage(), conflictStatus);
        return ResponseEntity.status(conflictStatus).body(details);
    }

    @ExceptionHandler(VoterBlockedException.class)
    public ResponseEntity<?> handleVoterBlocked(VoterBlockedException ex) {
        HttpStatus forbiddenStatus = HttpStatus.FORBIDDEN;
        ExceptionDetails details = new ExceptionDetails(ex.getMessage(), forbiddenStatus);
        return ResponseEntity.status(forbiddenStatus).body(details);
    }

    @ExceptionHandler(ElectionNotActiveException.class)
    public ResponseEntity<?> handleElectionNotActive(ElectionNotActiveException ex) {
        HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;
        ExceptionDetails details = new ExceptionDetails(ex.getMessage(), badRequestStatus);
        return ResponseEntity.status(badRequestStatus).body(details);
    }
}
