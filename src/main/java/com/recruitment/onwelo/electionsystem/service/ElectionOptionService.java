package com.recruitment.onwelo.electionsystem.service;

import com.recruitment.onwelo.electionsystem.dto.election.CreateElectionOptionRequest;
import com.recruitment.onwelo.electionsystem.dto.election.ElectionOptionDto;
import com.recruitment.onwelo.electionsystem.entity.Election;

import java.util.List;
import java.util.UUID;

public interface ElectionOptionService {
    List<ElectionOptionDto> addOptionsToElection(Election election, List<CreateElectionOptionRequest> options);

    List<ElectionOptionDto> getOptionsByElectionId(UUID electionId);

    void deleteOption(UUID electionId, UUID optionId);
}
