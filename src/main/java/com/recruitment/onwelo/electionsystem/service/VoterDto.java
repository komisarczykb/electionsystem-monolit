package com.recruitment.onwelo.electionsystem.service;

import com.recruitment.onwelo.electionsystem.entity.Voter;
import com.recruitment.onwelo.electionsystem.utils.VoterStatus;

import java.util.UUID;

public record VoterDto(UUID id, String firstName, String lastName, VoterStatus status) {
    public static VoterDto fromVoter(Voter v) {
        return new VoterDto(v.getId(), v.getFirstName(), v.getLastName(), v.getStatus());
    }
}
