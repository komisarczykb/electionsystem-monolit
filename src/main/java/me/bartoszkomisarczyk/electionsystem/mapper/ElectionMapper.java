package me.bartoszkomisarczyk.electionsystem.mapper;

import me.bartoszkomisarczyk.electionsystem.dto.election.CreateElectionRequest;
import me.bartoszkomisarczyk.electionsystem.dto.election.ElectionDto;
import me.bartoszkomisarczyk.electionsystem.entity.Election;

import java.util.LinkedList;
import java.util.List;

public class ElectionMapper {
    private ElectionMapper() {
    }

    public static ElectionDto toDto(Election election) {
        return new ElectionDto(election.getId(), election.getTitle(),
                election.getStartDate(), election.getEndDate(),
                election.getOptions().stream().map(ElectionOptionMapper::toDto).toList());
    }

    public static Election toEntity(CreateElectionRequest request) {
        return Election.builder()
                .title(request.title())
                .startDate(request.start())
                .endDate(request.end())
                .build();
    }

    public static List<ElectionDto> toDtoList(Iterable<Election> iterable) {
        List<ElectionDto> list = new LinkedList<>();
        iterable.forEach(e -> list.add(toDto(e)));
        return list;
    }
}
