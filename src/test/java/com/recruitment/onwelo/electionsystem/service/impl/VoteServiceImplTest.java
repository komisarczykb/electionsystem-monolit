package com.recruitment.onwelo.electionsystem.service.impl;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VoteServiceImplTest {

    @Mock
    private ElectionVotesRepository electionVotesRepository;
    @Mock
    private VoterRepository voterRepository;
    @Mock
    private ElectionRepository electionRepository;
    @Mock
    private ElectionOptionRepository electionOptionRepository;

    @InjectMocks
    private VoteServiceImpl voteService;

    private UUID voterId;
    private UUID electionId;
    private UUID optionId;

    @BeforeEach
    void setUp() {
        voterId = UUID.randomUUID();
        electionId = UUID.randomUUID();
        optionId = UUID.randomUUID();
    }

    @Test
    void shouldThrowVoterNotFoundException() {
        VoteRequest request = new VoteRequest(voterId, optionId);
        when(voterRepository.findById(voterId)).thenReturn(Optional.empty());

        assertThrows(VoterNotFoundException.class,
                () -> voteService.vote(electionId, request));
    }

    @Test
    void shouldThrowVoterBlockedException() {
        VoteRequest request = new VoteRequest(voterId, optionId);
        Voter blockedVoter = Voter.builder()
                .id(voterId)
                .status(VoterStatus.BLOCKED)
                .build();
        when(voterRepository.findById(voterId)).thenReturn(Optional.of(blockedVoter));

        assertThrows(VoterBlockedException.class,
                () -> voteService.vote(electionId, request));
    }

    @Test
    void shouldThrowElectionNotFoundException() {
        VoteRequest request = new VoteRequest(voterId, optionId);
        Voter voter = Voter.builder()
                .id(voterId)
                .status(VoterStatus.ACTIVE)
                .build();
        when(voterRepository.findById(voterId)).thenReturn(Optional.of(voter));
        when(electionRepository.findById(electionId)).thenReturn(Optional.empty());

        assertThrows(ElectionNotFoundException.class,
                () -> voteService.vote(electionId, request));
    }

    @Test
    void shouldThrowElectionInactiveException() {
        VoteRequest request = new VoteRequest(voterId, optionId);
        Voter voter = Voter.builder()
                .id(voterId)
                .status(VoterStatus.ACTIVE)
                .build();
        Election election = Election.builder()
                .id(electionId)
                .startDate(LocalDate.now().plusDays(10))
                .endDate(LocalDate.now().plusDays(20))
                .build();

        when(voterRepository.findById(voterId)).thenReturn(Optional.of(voter));
        when(electionRepository.findById(electionId)).thenReturn(Optional.of(election));

        assertThrows(ElectionNotActiveException.class,
                () -> voteService.vote(electionId, request));
    }

    @Test
    void shouldThrowAlreadyVotedException() {
        VoteRequest request = new VoteRequest(voterId, optionId);
        Voter voter = Voter.builder()
                .id(voterId)
                .status(VoterStatus.ACTIVE)
                .build();
        Election election = Election.builder()
                .id(electionId)
                .startDate(LocalDate.now().minusDays(1))
                .endDate(LocalDate.now().plusDays(1))
                .build();

        when(voterRepository.findById(voterId)).thenReturn(Optional.of(voter));
        when(electionRepository.findById(electionId)).thenReturn(Optional.of(election));
        when(electionVotesRepository.existsByVoterIdAndElectionId(voterId, electionId))
                .thenReturn(true);

        assertThrows(AlreadyVotedException.class,
                () -> voteService.vote(electionId, request));
    }

    @Test
    void shouldThrowOptionNotFoundException() {
        VoteRequest request = new VoteRequest(voterId, optionId);
        Voter voter = Voter.builder()
                .id(voterId)
                .status(VoterStatus.ACTIVE)
                .build();
        Election election = Election.builder()
                .id(electionId)
                .startDate(LocalDate.now().minusDays(1))
                .endDate(LocalDate.now().plusDays(1))
                .build();

        when(voterRepository.findById(voterId)).thenReturn(Optional.of(voter));
        when(electionRepository.findById(electionId)).thenReturn(Optional.of(election));
        when(electionVotesRepository.existsByVoterIdAndElectionId(voterId, electionId))
                .thenReturn(false);
        when(electionOptionRepository.findById(optionId)).thenReturn(Optional.empty());

        assertThrows(ElectionOptionNotFoundException.class,
                () -> voteService.vote(electionId, request));
    }

    @Test
    void shouldThrowElectionOptionNotFoundException() {
        UUID differentElectionId = UUID.randomUUID();
        VoteRequest request = new VoteRequest(voterId, optionId);
        Voter voter = Voter.builder()
                .id(voterId)
                .status(VoterStatus.ACTIVE)
                .build();
        Election election = Election.builder()
                .id(electionId)
                .startDate(LocalDate.now().minusDays(1))
                .endDate(LocalDate.now().plusDays(1))
                .build();
        Election differentElection = Election.builder()
                .id(differentElectionId)
                .build();
        ElectionOption option = ElectionOption.builder()
                .id(optionId)
                .election(differentElection)
                .build();

        when(voterRepository.findById(voterId)).thenReturn(Optional.of(voter));
        when(electionRepository.findById(electionId)).thenReturn(Optional.of(election));
        when(electionVotesRepository.existsByVoterIdAndElectionId(voterId, electionId))
                .thenReturn(false);
        when(electionOptionRepository.findById(optionId)).thenReturn(Optional.of(option));

        assertThrows(ElectionOptionNotFoundException.class,
                () -> voteService.vote(electionId, request));
    }

    @Test
    void shouldSaveVoteIdDb() {
        VoteRequest request = new VoteRequest(voterId, optionId);
        Voter voter = Voter.builder()
                .id(voterId)
                .status(VoterStatus.ACTIVE)
                .build();
        Election election = Election.builder()
                .id(electionId)
                .startDate(LocalDate.now().minusDays(1))
                .endDate(LocalDate.now().plusDays(1))
                .build();
        ElectionOption option = ElectionOption.builder()
                .id(optionId)
                .election(election)
                .build();

        when(voterRepository.findById(voterId)).thenReturn(Optional.of(voter));
        when(electionRepository.findById(electionId)).thenReturn(Optional.of(election));
        when(electionVotesRepository.existsByVoterIdAndElectionId(voterId, electionId))
                .thenReturn(false);
        when(electionOptionRepository.findById(optionId)).thenReturn(Optional.of(option));

        voteService.vote(electionId, request);

        verify(electionVotesRepository).save(any(ElectionVotes.class));
    }

    @Test
    void shouldReturnCorrectVotesCount() {
        when(electionRepository.existsById(electionId)).thenReturn(true);
        when(electionVotesRepository.countByElectionId(electionId)).thenReturn(42L);

        VoteCountDto result = voteService.getVoteCount(electionId);

        assertEquals(electionId, result.electionId());
        assertEquals(42L, result.totalVotes());
    }
}
