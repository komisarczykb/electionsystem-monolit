package com.recruitment.onwelo.electionsystem.service.impl;

import com.recruitment.onwelo.electionsystem.dto.election.CreateElectionRequest;
import com.recruitment.onwelo.electionsystem.dto.election.ElectionDto;
import com.recruitment.onwelo.electionsystem.exception.ElectionNotFoundException;
import com.recruitment.onwelo.electionsystem.mapper.ElectionMapper;
import com.recruitment.onwelo.electionsystem.repository.ElectionRepository;
import com.recruitment.onwelo.electionsystem.service.ElectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ElectionServiceImpl implements ElectionService {

    private final ElectionRepository electionRepository;
    private final ElectionMapper electionMapper;

    @Override
    public ElectionDto createElection(CreateElectionRequest request) {
        return electionMapper.toDto(electionRepository.save(electionMapper.toEntity(request)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ElectionDto> getAllElections() {
        return electionMapper.toDtoList(electionRepository.findAll());
    }

    @Override
    public void deleteElectionById(UUID id) {
        electionRepository.findById(id).ifPresent(electionRepository::delete);
    }

    @Override
    @Transactional(readOnly = true)
    public ElectionDto findElectionById(UUID id) {
        return electionMapper.toDto(electionRepository.findById(id).orElseThrow(() -> new ElectionNotFoundException(id)));
    }
}
