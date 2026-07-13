package me.bartoszkomisarczyk.electionsystem.dto.voter;

import me.bartoszkomisarczyk.electionsystem.entity.enums.VoterStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateVoterStatusRequest(@NotNull(message = "Status is mandatory") VoterStatus status) {
}
