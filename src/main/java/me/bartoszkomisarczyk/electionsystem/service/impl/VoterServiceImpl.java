package me.bartoszkomisarczyk.electionsystem.service.impl;

import me.bartoszkomisarczyk.electionsystem.dto.voter.CreateVoterRequest;
import me.bartoszkomisarczyk.electionsystem.dto.voter.UpdateVoterStatusRequest;
import me.bartoszkomisarczyk.electionsystem.dto.voter.VoterDto;
import me.bartoszkomisarczyk.electionsystem.entity.Voter;
import me.bartoszkomisarczyk.electionsystem.exception.VoterNotFoundException;
import me.bartoszkomisarczyk.electionsystem.mapper.VoterMapper;
import me.bartoszkomisarczyk.electionsystem.repository.VoterRepository;
import me.bartoszkomisarczyk.electionsystem.service.VoterService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class VoterServiceImpl implements VoterService {

    private final VoterRepository repository;

    @CacheEvict(value = "voters", allEntries = true)
    @Override
    @Transactional
    public VoterDto createNewVoter(CreateVoterRequest request) {
        return VoterMapper.toDto(repository.save(VoterMapper.toEntity(request)));
    }

    @CacheEvict(value = "voters", allEntries = true)
    @Override
    @Transactional
    public VoterDto updateVoterStatus(UUID id, UpdateVoterStatusRequest request) {
        Voter v = repository.findById(id).orElseThrow(() -> new VoterNotFoundException(id));
        v.setStatus(request.status());
        return VoterMapper.toDto(repository.save(v));
    }

    @Cacheable(value = "voters")
    @Override
    public List<VoterDto> getAllVoters() {
        ArrayList<Voter> list = (ArrayList<Voter>) repository.findAll();
        return list.stream().map(VoterMapper::toDto).toList();
    }
}
