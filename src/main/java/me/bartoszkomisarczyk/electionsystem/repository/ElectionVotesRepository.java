package me.bartoszkomisarczyk.electionsystem.repository;

import me.bartoszkomisarczyk.electionsystem.entity.ElectionVotedId;
import me.bartoszkomisarczyk.electionsystem.entity.ElectionVotes;
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
