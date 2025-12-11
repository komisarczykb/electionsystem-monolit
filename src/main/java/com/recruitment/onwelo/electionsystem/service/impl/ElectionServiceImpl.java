package com.recruitment.onwelo.electionsystem.service.impl;

import com.recruitment.onwelo.electionsystem.dto.election.CreateElectionRequest;
import com.recruitment.onwelo.electionsystem.dto.election.ElectionDto;
import com.recruitment.onwelo.electionsystem.entity.Election;
import com.recruitment.onwelo.electionsystem.exception.ElectionNotFoundException;
import com.recruitment.onwelo.electionsystem.mapper.ElectionMapper;
import com.recruitment.onwelo.electionsystem.mapper.ElectionOptionMapper;
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

    @Override
    public ElectionDto createElection(CreateElectionRequest request) {
        Election election = Election.builder()
                .title(request.title())
                .startDate(request.start())
                .endDate(request.end())
                .build();

        request.optionList().forEach(opt -> election.addOption(ElectionOptionMapper.toEntity(opt)));

        Election saved = electionRepository.save(election);
        return ElectionMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ElectionDto> getAllElections() {
        return ElectionMapper.toDtoList(electionRepository.findAll());
    }

    @Override
    public void deleteElectionById(UUID id) {
        electionRepository.findById(id).ifPresent(electionRepository::delete);
    }

    @Override
    @Transactional(readOnly = true)
    public ElectionDto findElectionById(UUID id) {
        return ElectionMapper.toDto(electionRepository.findById(id).orElseThrow(() -> new ElectionNotFoundException(id)));
    }
}
