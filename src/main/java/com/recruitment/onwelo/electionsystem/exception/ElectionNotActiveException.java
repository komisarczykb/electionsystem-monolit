package com.recruitment.onwelo.electionsystem.exception;

import java.util.UUID;

public class ElectionNotActiveException extends RuntimeException {
    public ElectionNotActiveException(UUID electionId) {
        super(String.format("Election with id %s is not currently active", electionId));
    }
}
