package me.bartoszkomisarczyk.electionsystem.service;

import me.bartoszkomisarczyk.electionsystem.dto.vote.ElectionVoteDto;
import me.bartoszkomisarczyk.electionsystem.dto.vote.VoteCountDto;
import me.bartoszkomisarczyk.electionsystem.dto.vote.VoteRequest;

import java.util.List;
import java.util.UUID;

public interface VoteService {
    void vote(UUID electionId, VoteRequest request);

    VoteCountDto getVoteCount(UUID electionId);

    List<ElectionVoteDto> getAllVotes(UUID electionId);
}
