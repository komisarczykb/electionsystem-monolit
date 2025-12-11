package com.recruitment.onwelo.electionsystem.mapper;

import com.recruitment.onwelo.electionsystem.dto.voter.CreateVoterRequest;
import com.recruitment.onwelo.electionsystem.dto.voter.VoterDto;
import com.recruitment.onwelo.electionsystem.entity.Voter;
import com.recruitment.onwelo.electionsystem.entity.enums.VoterStatus;

public class VoterMapper {
    private VoterMapper() {};
    public static Voter toEntity(CreateVoterRequest request) {
        return Voter.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .status(VoterStatus.ACTIVE)
                .build();
    }

    public static VoterDto toDto(Voter voter) {
        return new VoterDto(
                voter.getId(),
                voter.getFirstName(),
                voter.getLastName(),
                voter.getStatus()
        );
    }
}
