package com.recruitment.onwelo.electionsystem.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ElectionVotedId implements Serializable {
    private UUID voterId;
    private UUID electionId;
}
