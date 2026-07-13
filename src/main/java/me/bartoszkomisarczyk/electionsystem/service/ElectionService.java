package me.bartoszkomisarczyk.electionsystem.service;

import me.bartoszkomisarczyk.electionsystem.dto.election.CreateElectionRequest;
import me.bartoszkomisarczyk.electionsystem.dto.election.ElectionDto;

import java.util.List;
import java.util.UUID;

public interface ElectionService {
    ElectionDto createElection(CreateElectionRequest request);

    List<ElectionDto> getAllElections();

    void deleteElectionById(UUID id);

    ElectionDto findElectionById(UUID id);
}
