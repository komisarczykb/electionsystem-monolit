package me.bartoszkomisarczyk.electionsystem.exception;

import java.util.UUID;

public class VoterBlockedException extends RuntimeException {
    public VoterBlockedException(UUID voterId) {
        super(String.format("Voter with id %s is blocked and cannot vote", voterId));
    }
}
