package com.onwello.rekrutacja.wybory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
public class Election {
    @Id
    UUID id;

    LocalDate startDate;
    LocalDate endDate;
}
