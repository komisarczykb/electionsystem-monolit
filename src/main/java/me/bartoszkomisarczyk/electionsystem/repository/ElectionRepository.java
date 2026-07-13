package me.bartoszkomisarczyk.electionsystem.repository;

import me.bartoszkomisarczyk.electionsystem.entity.Election;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ElectionRepository extends CrudRepository<Election, UUID> {
}
