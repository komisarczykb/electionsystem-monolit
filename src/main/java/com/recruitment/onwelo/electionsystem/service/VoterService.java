package com.recruitment.onwelo.electionsystem.service;

import com.recruitment.onwelo.electionsystem.dto.UpdateVoterStatusRequest;
import com.recruitment.onwelo.electionsystem.dto.CreateVoterRequest;

import java.util.UUID;

public interface VoterService {
    VoterDto createNewVoter(CreateVoterRequest request);

    VoterDto updateVoterStatus(UUID id, UpdateVoterStatusRequest request);
}
