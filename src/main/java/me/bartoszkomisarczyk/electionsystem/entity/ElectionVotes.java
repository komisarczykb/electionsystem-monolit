package me.bartoszkomisarczyk.electionsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "election_votes")
@IdClass(ElectionVotedId.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectionVotes {

    @Id
    @Column(name = "voter_id")
    private UUID voterId;

    @Id
    @Column(name = "election_id")
    private UUID electionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voter_id", insertable = false, updatable = false)
    private Voter voter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "election_id", insertable = false, updatable = false)
    private Election election;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id", nullable = false)
    private ElectionOption option;
}
