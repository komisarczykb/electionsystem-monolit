package com.onwello.rekrutacja.wybory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Voter {
    @Id
    UUID id;

    String firstName;
    String lastName;
}
