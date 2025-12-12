package com.recruitment.onwelo.electionsystem.service;

import com.recruitment.onwelo.electionsystem.dto.vote.ElectionVoteDto;
import com.recruitment.onwelo.electionsystem.dto.vote.VoteCountDto;
import com.recruitment.onwelo.electionsystem.dto.vote.VoteRequest;

import java.util.List;
import java.util.UUID;

public interface VoteService {
    void vote(UUID electionId, VoteRequest request);

    VoteCountDto getVoteCount(UUID electionId);

    List<ElectionVoteDto> getAllVotes(UUID electionId);
}
