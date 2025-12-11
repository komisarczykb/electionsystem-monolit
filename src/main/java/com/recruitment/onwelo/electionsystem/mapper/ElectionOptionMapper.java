package com.recruitment.onwelo.electionsystem.mapper;

import com.recruitment.onwelo.electionsystem.dto.election.CreateElectionOptionRequest;
import com.recruitment.onwelo.electionsystem.dto.election.ElectionOptionDto;
import com.recruitment.onwelo.electionsystem.entity.ElectionOption;

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
