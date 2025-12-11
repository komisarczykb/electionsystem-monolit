package com.recruitment.onwelo.electionsystem.dto.election;

import com.recruitment.onwelo.electionsystem.entity.ElectionOption;

import java.time.LocalDate;
import java.util.List;

public record CreateElectionRequest(LocalDate start, LocalDate end,
                                    String title, List<ElectionOption> optionList) {
}
