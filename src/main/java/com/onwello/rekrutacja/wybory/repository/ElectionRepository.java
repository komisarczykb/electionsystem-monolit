package com.onwello.rekrutacja.wybory.repository;

import com.onwello.rekrutacja.wybory.entity.Election;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ElectionRepository extends CrudRepository<Election, UUID> {
}
