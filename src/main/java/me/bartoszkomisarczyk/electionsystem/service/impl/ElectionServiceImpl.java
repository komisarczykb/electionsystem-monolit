package me.bartoszkomisarczyk.electionsystem.service.impl;

import me.bartoszkomisarczyk.electionsystem.dto.election.CreateElectionRequest;
import me.bartoszkomisarczyk.electionsystem.dto.election.ElectionDto;
import me.bartoszkomisarczyk.electionsystem.entity.Election;
import me.bartoszkomisarczyk.electionsystem.exception.ElectionNotFoundException;
import me.bartoszkomisarczyk.electionsystem.mapper.ElectionMapper;
import me.bartoszkomisarczyk.electionsystem.mapper.ElectionOptionMapper;
import me.bartoszkomisarczyk.electionsystem.repository.ElectionRepository;
import me.bartoszkomisarczyk.electionsystem.service.ElectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ElectionServiceImpl implements ElectionService {

    private final ElectionRepository electionRepository;

    @CacheEvict(value = "elections", allEntries = true)
    @Override
    @Transactional
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

    @Cacheable(value = "elections")
    @Override
    public List<ElectionDto> getAllElections() {
        return ElectionMapper.toDtoList(electionRepository.findAll());
    }

    @Caching(evict = {
            @CacheEvict(value = "elections", allEntries = true),
            @CacheEvict(value = "electionById", key = "#id"),
            @CacheEvict(value = "electionOptions", key = "#id"),
            @CacheEvict(value = "electionVotes", key = "#id")
    })
    @Override
    @Transactional
    public void deleteElectionById(UUID id) {
        electionRepository.findById(id).ifPresent(electionRepository::delete);
    }

    @Cacheable(value = "electionById", key = "#id")
    @Override
    public ElectionDto findElectionById(UUID id) {
        return ElectionMapper.toDto(electionRepository.findById(id).orElseThrow(() -> new ElectionNotFoundException(id)));
    }
}
