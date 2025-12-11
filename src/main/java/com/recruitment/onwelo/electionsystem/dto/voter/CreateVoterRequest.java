package com.recruitment.onwelo.electionsystem.dto.voter;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateVoterRequest(
        @NotNull @Size(min = 2, max = 16, message = "First name must be between 2 and 16 characters long")
        String firstName,
        @NotNull @Size(min = 2, max = 32)
        String lastName) { }
