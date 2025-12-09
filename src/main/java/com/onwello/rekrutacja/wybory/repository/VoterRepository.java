package com.onwello.rekrutacja.wybory.repository;

import com.onwello.rekrutacja.wybory.entity.Voter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VoterRepository extends CrudRepository<Voter, UUID> {
}
