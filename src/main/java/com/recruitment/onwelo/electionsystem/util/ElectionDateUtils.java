package com.recruitment.onwelo.electionsystem.util;

import com.recruitment.onwelo.electionsystem.entity.Election;

import java.time.LocalDate;

public class ElectionDateUtils {
    private ElectionDateUtils() {}

    public static boolean isInValidVotingWindow(Election e) {
        return LocalDate.now().isBefore(e.getEndDate().plusDays(1)) && LocalDate.now().isAfter(e.getStartDate().minusDays(1));
    }
}
