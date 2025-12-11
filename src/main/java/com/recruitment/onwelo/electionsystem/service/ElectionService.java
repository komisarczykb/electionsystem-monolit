package com.recruitment.onwelo.electionsystem.service;

import com.recruitment.onwelo.electionsystem.dto.election.CreateElectionRequest;
import com.recruitment.onwelo.electionsystem.dto.election.ElectionDto;

import java.util.List;
import java.util.UUID;

public interface ElectionService {
    ElectionDto createElection(CreateElectionRequest request);

    List<ElectionDto> getAllElections();

    void deleteElectionById(UUID id);

    ElectionDto findElectionById(UUID id);
}
