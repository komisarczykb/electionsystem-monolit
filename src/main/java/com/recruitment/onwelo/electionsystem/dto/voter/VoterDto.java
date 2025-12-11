package com.recruitment.onwelo.electionsystem.dto.voter;

import com.recruitment.onwelo.electionsystem.entity.enums.VoterStatus;

import java.util.UUID;

public record VoterDto(UUID id, String firstName, String lastName, VoterStatus status) {
}
