package com.recruitment.onwelo.electionsystem.dto.vote;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record VoteRequest(
        @NotNull(message = "voterId cannot be null")
        UUID voterId,
        @NotNull(message = "optionId cannot be null")
        UUID optionId
) {
}
