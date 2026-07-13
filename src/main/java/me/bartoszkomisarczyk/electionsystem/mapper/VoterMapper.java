package me.bartoszkomisarczyk.electionsystem.mapper;

import me.bartoszkomisarczyk.electionsystem.dto.voter.CreateVoterRequest;
import me.bartoszkomisarczyk.electionsystem.dto.voter.VoterDto;
import me.bartoszkomisarczyk.electionsystem.entity.Voter;
import me.bartoszkomisarczyk.electionsystem.entity.enums.VoterStatus;

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
