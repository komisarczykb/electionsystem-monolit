package me.bartoszkomisarczyk.electionsystem.dto.voter;

import me.bartoszkomisarczyk.electionsystem.entity.enums.VoterStatus;

import java.util.UUID;

public record VoterDto(UUID id, String firstName, String lastName, VoterStatus status) {
}
