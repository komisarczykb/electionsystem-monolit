package com.recruitment.onwelo.electionsystem.service.impl;

import com.recruitment.onwelo.electionsystem.dto.vote.ElectionVoteDto;
import com.recruitment.onwelo.electionsystem.dto.vote.VoteCountDto;
import com.recruitment.onwelo.electionsystem.dto.vote.VoteRequest;
import com.recruitment.onwelo.electionsystem.entity.Election;
import com.recruitment.onwelo.electionsystem.entity.ElectionOption;
import com.recruitment.onwelo.electionsystem.entity.ElectionVotes;
import com.recruitment.onwelo.electionsystem.entity.Voter;
import com.recruitment.onwelo.electionsystem.entity.enums.VoterStatus;
import com.recruitment.onwelo.electionsystem.exception.*;
import com.recruitment.onwelo.electionsystem.repository.ElectionOptionRepository;
import com.recruitment.onwelo.electionsystem.repository.ElectionRepository;
import com.recruitment.onwelo.electionsystem.repository.ElectionVotesRepository;
import com.recruitment.onwelo.electionsystem.repository.VoterRepository;
import com.recruitment.onwelo.electionsystem.service.VoteService;
import com.recruitment.onwelo.electionsystem.util.ElectionDateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final ElectionVotesRepository electionVotesRepository;
    private final VoterRepository voterRepository;
    private final ElectionRepository electionRepository;
    private final ElectionOptionRepository electionOptionRepository;

    @Override
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
    @Transactional(readOnly = true)
    public VoteCountDto getVoteCount(UUID electionId) {
        if (!electionRepository.existsById(electionId)) throw new ElectionNotFoundException(electionId);

        return new VoteCountDto(electionId, electionVotesRepository.countByElectionId(electionId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ElectionVoteDto> getAllVotes(UUID electionId) {
        if (!electionRepository.existsById(electionId)) throw new ElectionNotFoundException(electionId);

        return electionVotesRepository.findAllByElectionId(electionId).stream().map(ElectionVoteDto::fromEntity).toList();
    }
}
