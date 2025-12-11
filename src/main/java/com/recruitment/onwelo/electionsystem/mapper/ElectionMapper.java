package com.recruitment.onwelo.electionsystem.mapper;

import com.recruitment.onwelo.electionsystem.dto.election.CreateElectionRequest;
import com.recruitment.onwelo.electionsystem.dto.election.ElectionDto;
import com.recruitment.onwelo.electionsystem.dto.election.ElectionOptionDto;
import com.recruitment.onwelo.electionsystem.entity.Election;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class ElectionMapper {
    public ElectionDto toDto(Election election) {
        return new ElectionDto(election.getId(), election.getTitle(),
                election.getStartDate(), election.getEndDate(),
                election.getOptions().stream().map(ElectionOptionDto::formElectionOption).toList());
    }

    public Election toEntity(CreateElectionRequest request) {
        return Election.builder()
                .title(request.title())
                .startDate(request.start())
                .endDate(request.end())
                .options(request.optionList())
                .build();
    }

    public List<ElectionDto> toDtoList(Iterable<Election> iterable) {
        List<ElectionDto> list = new LinkedList<>();
        iterable.forEach(e -> list.add(toDto(e)));
        return list;
    }
}
