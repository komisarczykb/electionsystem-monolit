package com.recruitment.onwelo.electionsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Election {
    @Id
    private UUID id;
    private LocalDate startDate;
    private LocalDate endDate;
}
