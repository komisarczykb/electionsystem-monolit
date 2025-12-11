package com.recruitment.onwelo.electionsystem.dto.election;

import java.time.LocalDate;

public record CreateElectionRequest(LocalDate start, LocalDate end, String title) {
}
