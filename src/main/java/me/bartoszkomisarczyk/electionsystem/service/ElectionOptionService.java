package me.bartoszkomisarczyk.electionsystem.service;

import me.bartoszkomisarczyk.electionsystem.dto.election.CreateElectionOptionRequest;
import me.bartoszkomisarczyk.electionsystem.dto.election.ElectionOptionDto;

import java.util.List;
import java.util.UUID;

public interface ElectionOptionService {
    List<ElectionOptionDto> addOptionsToElection(UUID electionId, List<CreateElectionOptionRequest> options);

    List<ElectionOptionDto> getOptionsByElectionId(UUID electionId);

    void deleteOption(UUID optionId);
}
