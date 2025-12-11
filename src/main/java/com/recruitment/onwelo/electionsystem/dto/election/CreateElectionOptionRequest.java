package com.recruitment.onwelo.electionsystem.dto.election;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateElectionOptionRequest(
        @NotNull @Size(min = 1, max = 32, message = "Option must be between 1 and 32 charasters.") String option) {
}
