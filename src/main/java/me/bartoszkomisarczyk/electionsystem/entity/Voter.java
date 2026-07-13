package me.bartoszkomisarczyk.electionsystem.entity;

import me.bartoszkomisarczyk.electionsystem.entity.enums.VoterStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotEmpty
    @Column(nullable = false)
    private String firstName;
    @NotEmpty
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    @Builder.Default
    private VoterStatus status = VoterStatus.ACTIVE;
}
