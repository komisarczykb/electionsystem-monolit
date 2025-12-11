package com.recruitment.onwelo.electionsystem.service.impl;

import com.recruitment.onwelo.electionsystem.dto.CreateVoterRequest;
import com.recruitment.onwelo.electionsystem.dto.UpdateVoterStatusRequest;
import com.recruitment.onwelo.electionsystem.entity.Voter;
import com.recruitment.onwelo.electionsystem.exception.VoterNotFoundException;
import com.recruitment.onwelo.electionsystem.repository.VoterRepository;
import com.recruitment.onwelo.electionsystem.service.VoterDto;
import com.recruitment.onwelo.electionsystem.service.VoterService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VoterServiceImpl implements VoterService {

    private final VoterRepository repository;

    public VoterServiceImpl(VoterRepository voterRepository) {
        repository = voterRepository;
    }

    @Override
    public VoterDto createNewVoter(CreateVoterRequest request) {
        Voter saved = repository.save(Voter.fromVoterCreationRequest(request));
        return VoterDto.fromVoter(saved);
    }

    @Override
    public VoterDto updateVoterStatus(UUID id, UpdateVoterStatusRequest request) {
        Voter v = repository.findById(id).orElseThrow(() -> new VoterNotFoundException(id));
        v.setStatus(request.status());
        return VoterDto.fromVoter(repository.save(v));
    }
}
