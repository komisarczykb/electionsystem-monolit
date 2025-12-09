package com.onwello.rekrutacja.wybory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumns;

@PrimaryKeyJoinColumns({
        @PrimaryKeyJoinColumn(name = "voter", referencedColumnName = "voter_id"),
        @PrimaryKeyJoinColumn(name = "election", referencedColumnName = "election_id")
})
@Entity
public class ElectionVotes {
    Voter voter;
    Election election;
    ElectionOption option;
}
