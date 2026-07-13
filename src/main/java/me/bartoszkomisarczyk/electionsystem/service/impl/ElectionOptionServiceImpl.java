package me.bartoszkomisarczyk.electionsystem.service.impl;

import me.bartoszkomisarczyk.electionsystem.dto.election.CreateElectionOptionRequest;
import me.bartoszkomisarczyk.electionsystem.dto.election.ElectionOptionDto;
import me.bartoszkomisarczyk.electionsystem.entity.Election;
import me.bartoszkomisarczyk.electionsystem.entity.ElectionOption;
import me.bartoszkomisarczyk.electionsystem.exception.ElectionNotFoundException;
import me.bartoszkomisarczyk.electionsystem.mapper.ElectionOptionMapper;
import me.bartoszkomisarczyk.electionsystem.repository.ElectionOptionRepository;
import me.bartoszkomisarczyk.electionsystem.repository.ElectionRepository;
import me.bartoszkomisarczyk.electionsystem.service.ElectionOptionService;
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
public class ElectionOptionServiceImpl implements ElectionOptionService {

    private final ElectionOptionRepository electionOptionRepository;
    private final ElectionRepository electionRepository;

    @Caching(evict = {
            @CacheEvict(value = "electionOptions", key = "#electionId"),
            @CacheEvict(value = "electionById", key = "#electionId")
    })
    @Override
    @Transactional
    public List<ElectionOptionDto> addOptionsToElection(UUID electionId, List<CreateElectionOptionRequest> options) {
        Election election = electionRepository.findById(electionId)
                .orElseThrow(() -> new ElectionNotFoundException(electionId));

        List<ElectionOption> electionOptions = options.stream()
                .map(opt -> ElectionOption.builder()
                        .option(opt.option())
                        .election(election)
                        .build())
                .toList();

        return ((List<ElectionOption>) electionOptionRepository.saveAll(electionOptions))
                .stream()
                .map(ElectionOptionMapper::toDto)
                .toList();
    }

    @Cacheable(value = "electionOptions", key = "#electionId")
    @Override
    public List<ElectionOptionDto> getOptionsByElectionId(UUID electionId) {
        return electionOptionRepository.findAllByElectionId(electionId).stream().map(ElectionOptionMapper::toDto).toList();
    }

    @CacheEvict(value = "electionOptions", allEntries = true)
    @Override
    @Transactional
    public void deleteOption(UUID optionId) {
         electionOptionRepository.deleteById(optionId);
    }
}

