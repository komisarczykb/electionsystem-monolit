package com.recruitment.onwelo.electionsystem.repository;

import com.recruitment.onwelo.electionsystem.entity.Election;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ElectionRepository extends CrudRepository<Election, UUID> {
}
