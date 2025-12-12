package com.recruitment.onwelo.electionsystem.exception;

import java.util.UUID;

public class ElectionOptionNotFoundException extends RuntimeException {
    public ElectionOptionNotFoundException(UUID optionId) {
        super(String.format("Election option with id %s not found", optionId));
    }
}
