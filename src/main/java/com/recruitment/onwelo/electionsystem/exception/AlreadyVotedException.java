package com.recruitment.onwelo.electionsystem.exception;

import java.util.UUID;

public class AlreadyVotedException extends RuntimeException {
    public AlreadyVotedException(UUID voterId, UUID electionId) {
        super(String.format("Voter with id %s has already voted in election %s", voterId, electionId));
    }
}
