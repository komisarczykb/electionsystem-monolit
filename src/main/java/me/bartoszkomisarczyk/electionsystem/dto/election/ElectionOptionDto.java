package me.bartoszkomisarczyk.electionsystem.dto.election;

import me.bartoszkomisarczyk.electionsystem.entity.ElectionOption;

import java.util.UUID;

public record ElectionOptionDto(UUID id, String option) {
    public static ElectionOptionDto formElectionOption(ElectionOption electionOption) {
        return new ElectionOptionDto(electionOption.getId(), electionOption.getOption());
    }
}
