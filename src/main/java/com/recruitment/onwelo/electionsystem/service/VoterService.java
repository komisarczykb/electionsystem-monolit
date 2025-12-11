package com.recruitment.onwelo.electionsystem.service;

import com.recruitment.onwelo.electionsystem.dto.voter.CreateVoterRequest;
import com.recruitment.onwelo.electionsystem.dto.voter.UpdateVoterStatusRequest;
import com.recruitment.onwelo.electionsystem.dto.voter.VoterDto;

import java.util.List;
import java.util.UUID;

public interface VoterService {
    VoterDto createNewVoter(CreateVoterRequest request);

    VoterDto updateVoterStatus(UUID id, UpdateVoterStatusRequest request);

    List<VoterDto> getAllVoters();

}
