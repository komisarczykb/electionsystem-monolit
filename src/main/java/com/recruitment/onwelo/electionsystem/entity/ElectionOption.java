package com.recruitment.onwelo.electionsystem.entity;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;


public class ElectionOption {
    @Id
    private long id;
    private Election election;
    @NotEmpty
    private String option;
}
