package me.bartoszkomisarczyk.electionsystem.exception;

import java.util.UUID;

public class VoterNotFoundException extends RuntimeException {
    public VoterNotFoundException(UUID id) {
        super(String.format("Voter with id %s not found.", id));
    }
}
