package com.recruitment.onwelo.electionsystem.entity;

import com.recruitment.onwelo.electionsystem.dto.CreateVoterRequest;
import com.recruitment.onwelo.electionsystem.utils.VoterStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    VoterStatus status;

    public static Voter fromVoterCreationRequest(CreateVoterRequest voterCrateRequest) {
        Voter v = new Voter();
        v.setFirstName(voterCrateRequest.firstName());
        v.setLastName(voterCrateRequest.lastName());
        v.setStatus(VoterStatus.ACTIVE);
        return v;
    }
}
