package me.bartoszkomisarczyk.electionsystem.mapper;

import me.bartoszkomisarczyk.electionsystem.dto.election.CreateElectionOptionRequest;
import me.bartoszkomisarczyk.electionsystem.dto.election.ElectionOptionDto;
import me.bartoszkomisarczyk.electionsystem.entity.ElectionOption;

public class ElectionOptionMapper {
    private ElectionOptionMapper(){};
    public static ElectionOptionDto toDto(ElectionOption electionOption) {
        if (electionOption == null)
            return null;

        return new ElectionOptionDto(electionOption.getId(), electionOption.getOption());
    }

    public static ElectionOption toEntity(CreateElectionOptionRequest request) {
        return ElectionOption.builder()
                .option(request.option())
                .build();
    }
}
