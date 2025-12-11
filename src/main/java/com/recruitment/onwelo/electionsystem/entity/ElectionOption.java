package com.recruitment.onwelo.electionsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class ElectionOption {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotEmpty
    private String option;

    @ManyToOne
    @JsonIgnore
    private Election election;
}
