package com.recruitment.onwelo.electionsystem.service.impl;

import com.recruitment.onwelo.electionsystem.dto.voter.CreateVoterRequest;
import com.recruitment.onwelo.electionsystem.dto.voter.UpdateVoterStatusRequest;
import com.recruitment.onwelo.electionsystem.dto.voter.VoterDto;
import com.recruitment.onwelo.electionsystem.entity.Voter;
import com.recruitment.onwelo.electionsystem.exception.VoterNotFoundException;
import com.recruitment.onwelo.electionsystem.repository.VoterRepository;
import com.recruitment.onwelo.electionsystem.service.VoterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class VoterServiceImpl implements VoterService {

    private final VoterRepository repository;

    @Override
    @Transactional
    public VoterDto createNewVoter(CreateVoterRequest request) {
        Voter saved = repository.save(Voter.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .build());
        return VoterDto.fromVoter(saved);
    }

    @Override
    @Transactional
    public VoterDto updateVoterStatus(UUID id, UpdateVoterStatusRequest request) {
        Voter v = repository.findById(id).orElseThrow(() -> new VoterNotFoundException(id));
        v.setStatus(request.status());
        return VoterDto.fromVoter(repository.save(v));
    }

    @Override
    @Transactional(readOnly = true)
    public List<VoterDto> getAllVoters() {
        ArrayList<Voter> list = (ArrayList<Voter>) repository.findAll();
        return list.stream().map(VoterDto::fromVoter).toList();
    }
}
