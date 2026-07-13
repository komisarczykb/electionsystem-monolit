package me.bartoszkomisarczyk.electionsystem.dto.vote;

import me.bartoszkomisarczyk.electionsystem.entity.ElectionVotes;

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
