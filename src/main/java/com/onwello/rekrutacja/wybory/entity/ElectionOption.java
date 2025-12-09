package com.onwello.rekrutacja.wybory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class ElectionOption {
    @Id
    long id;

    Election election;

    String option;
}
