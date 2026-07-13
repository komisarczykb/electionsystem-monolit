package me.bartoszkomisarczyk.electionsystem.dto.election;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ElectionDto(UUID id, String title, LocalDate start, LocalDate end,
                          List<ElectionOptionDto> electionOptionList) {
}
