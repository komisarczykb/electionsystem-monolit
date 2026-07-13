package me.bartoszkomisarczyk.electionsystem.service.impl;

import me.bartoszkomisarczyk.electionsystem.dto.vote.ElectionVoteDto;
import me.bartoszkomisarczyk.electionsystem.dto.vote.VoteCountDto;
import me.bartoszkomisarczyk.electionsystem.dto.vote.VoteRequest;
import me.bartoszkomisarczyk.electionsystem.entity.Election;
import me.bartoszkomisarczyk.electionsystem.entity.ElectionOption;
import me.bartoszkomisarczyk.electionsystem.entity.ElectionVotes;
import me.bartoszkomisarczyk.electionsystem.entity.Voter;
import me.bartoszkomisarczyk.electionsystem.entity.enums.VoterStatus;
import me.bartoszkomisarczyk.electionsystem.exception.*;
import me.bartoszkomisarczyk.electionsystem.repository.ElectionOptionRepository;
import me.bartoszkomisarczyk.electionsystem.repository.ElectionRepository;
import me.bartoszkomisarczyk.electionsystem.repository.ElectionVotesRepository;
import me.bartoszkomisarczyk.electionsystem.repository.VoterRepository;
import me.bartoszkomisarczyk.electionsystem.service.VoteService;
import me.bartoszkomisarczyk.electionsystem.util.ElectionDateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final ElectionVotesRepository electionVotesRepository;
    private final VoterRepository voterRepository;
    private final ElectionRepository electionRepository;
    private final ElectionOptionRepository electionOptionRepository;

    @CacheEvict(value = "electionVotes", key = "#electionId")
    @Override
    @Transactional
    public void vote(UUID electionId, VoteRequest request) {
        Voter voter = voterRepository.findById(request.voterId())
                .orElseThrow(() -> new VoterNotFoundException(request.voterId()));

        if (voter.getStatus() == VoterStatus.BLOCKED) throw new VoterBlockedException(request.voterId());

        Election election = electionRepository.findById(electionId)
                .orElseThrow(() -> new ElectionNotFoundException(electionId));

        if (!ElectionDateUtils.isInValidVotingWindow(election)) throw new ElectionNotActiveException(electionId);

        if (electionVotesRepository.existsByVoterIdAndElectionId(request.voterId(), electionId))
            throw new AlreadyVotedException(request.voterId(), electionId);

        ElectionOption option = electionOptionRepository.findById(request.optionId())
                .orElseThrow(() -> new ElectionOptionNotFoundException(request.optionId()));

        if (!option.getElection().getId().equals(electionId))
            throw new ElectionOptionNotFoundException(request.optionId());

        ElectionVotes vote = ElectionVotes.builder()
                .voterId(request.voterId())
                .electionId(electionId)
                .option(option)
                .build();
        electionVotesRepository.save(vote);
    }

    @Override
    public VoteCountDto getVoteCount(UUID electionId) {
        if (!electionRepository.existsById(electionId)) throw new ElectionNotFoundException(electionId);

        return new VoteCountDto(electionId, electionVotesRepository.countByElectionId(electionId));
    }

    @Cacheable(value = "electionVotes", key = "#electionId")
    @Override
    public List<ElectionVoteDto> getAllVotes(UUID electionId) {
        if (!electionRepository.existsById(electionId)) throw new ElectionNotFoundException(electionId);

        return electionVotesRepository.findAllByElectionId(electionId).stream().map(ElectionVoteDto::fromEntity).toList();
    }
}
