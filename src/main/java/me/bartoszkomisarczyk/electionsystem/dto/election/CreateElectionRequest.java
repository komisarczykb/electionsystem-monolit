package me.bartoszkomisarczyk.electionsystem.dto.election;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record CreateElectionRequest(@NotNull(message = "start date cannot be null") LocalDate start,
                                    @NotNull(message = "end date cannot be null") LocalDate end,
                                    @NotNull @Size(min = 5, max = 64, message = "title must be between 5 and 64 characters") String title,
                                    @NotNull List<CreateElectionOptionRequest> optionList) {
}
