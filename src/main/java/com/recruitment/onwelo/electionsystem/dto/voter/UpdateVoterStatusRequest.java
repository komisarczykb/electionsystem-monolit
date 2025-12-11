package com.recruitment.onwelo.electionsystem.dto.voter;

import com.recruitment.onwelo.electionsystem.utils.VoterStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateVoterStatusRequest(@NotNull(message = "Status is mandatory") VoterStatus status) {
}
