package com.recruitment.onwelo.electionsystem.dto.vote;

import com.recruitment.onwelo.electionsystem.entity.ElectionVotes;

import java.util.UUID;

public record ElectionVoteDto(
        UUID voterId,
        UUID electionId,
        UUID optionId,
        String optionName
) {
    public static ElectionVoteDto fromEntity(ElectionVotes vote) {
        return new ElectionVoteDto(
                vote.getVoterId(),
                vote.getElectionId(),
                vote.getOption().getId(),
                vote.getOption().getOption()
        );
    }
}
