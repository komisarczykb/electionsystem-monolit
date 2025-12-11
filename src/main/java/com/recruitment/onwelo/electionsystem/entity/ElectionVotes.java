package com.recruitment.onwelo.electionsystem.entity;

/*
@PrimaryKeyJoinColumns({
        @PrimaryKeyJoinColumn(name = "voter", referencedColumnName = "voter_id"),
        @PrimaryKeyJoinColumn(name = "election", referencedColumnName = "election_id")
})
*/

public class ElectionVotes {
    // a person/voter can vote once in one elections -> compound(?) key (voter_id, election_id) solves guarantees exception on another vote try
    private Voter voter;
    private Election election;
    private ElectionOption option;
}
