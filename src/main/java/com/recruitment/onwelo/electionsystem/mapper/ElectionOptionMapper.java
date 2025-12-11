package com.recruitment.onwelo.electionsystem.mapper;

import com.recruitment.onwelo.electionsystem.dto.election.ElectionOptionDto;
import com.recruitment.onwelo.electionsystem.entity.ElectionOption;
import org.springframework.stereotype.Component;

@Component
public class ElectionOptionMapper {
    public ElectionOptionDto toDto(ElectionOption electionOption) {
        if (electionOption == null)
            return null;

        return new ElectionOptionDto(electionOption.getId(), electionOption.getOption());
    }
}
