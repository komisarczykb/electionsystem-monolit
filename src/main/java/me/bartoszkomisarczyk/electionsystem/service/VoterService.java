package me.bartoszkomisarczyk.electionsystem.service;

import me.bartoszkomisarczyk.electionsystem.dto.voter.CreateVoterRequest;
import me.bartoszkomisarczyk.electionsystem.dto.voter.UpdateVoterStatusRequest;
import me.bartoszkomisarczyk.electionsystem.dto.voter.VoterDto;

import java.util.List;
import java.util.UUID;

public interface VoterService {
    VoterDto createNewVoter(CreateVoterRequest request);

    VoterDto updateVoterStatus(UUID id, UpdateVoterStatusRequest request);

    List<VoterDto> getAllVoters();

}
