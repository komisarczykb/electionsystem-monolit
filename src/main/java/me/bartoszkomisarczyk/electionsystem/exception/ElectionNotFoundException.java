package me.bartoszkomisarczyk.electionsystem.exception;

import java.util.UUID;

public class ElectionNotFoundException extends RuntimeException {
    public ElectionNotFoundException(UUID id) {
        super(String.format("Election with id %s not found in database.", id));
    }
}
