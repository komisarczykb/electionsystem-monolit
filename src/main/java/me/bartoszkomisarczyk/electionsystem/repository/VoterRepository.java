package me.bartoszkomisarczyk.electionsystem.repository;

import me.bartoszkomisarczyk.electionsystem.entity.Voter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VoterRepository extends CrudRepository<Voter, UUID> {
}
