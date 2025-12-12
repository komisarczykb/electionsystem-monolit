package com.recruitment.onwelo.electionsystem.repository;

import com.recruitment.onwelo.electionsystem.entity.ElectionVotedId;
import com.recruitment.onwelo.electionsystem.entity.ElectionVotes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ElectionVotesRepository extends CrudRepository<ElectionVotes, ElectionVotedId> {

    boolean existsByVoterIdAndElectionId(UUID voterId, UUID electionId);

    List<ElectionVotes> findAllByElectionId(UUID electionId);

    long countByElectionId(UUID electionId);
}
