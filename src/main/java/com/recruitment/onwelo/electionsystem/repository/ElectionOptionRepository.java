package com.recruitment.onwelo.electionsystem.repository;

import com.recruitment.onwelo.electionsystem.entity.ElectionOption;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ElectionOptionRepository extends CrudRepository<ElectionOption, UUID> {
    List<ElectionOption> findAllByElectionId(UUID electionId);
}
