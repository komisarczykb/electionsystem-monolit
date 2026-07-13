package me.bartoszkomisarczyk.electionsystem.dto.vote;

import java.util.UUID;

public record VoteCountDto(
        UUID electionId,
        long totalVotes
) {
}
