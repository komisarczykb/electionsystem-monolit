package com.recruitment.onwelo.electionsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    @Size(min = 5, max = 64, message = "Election title must be between 5 and 64 characters")
    private String title;
    @NotNull(message = "startDate cannot be null")
    private LocalDate startDate;
    @NotNull(message = "endDate cannot be null")
    private LocalDate endDate;

    @Builder.Default
    @OneToMany(mappedBy = "election",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    List<ElectionOption> options = new ArrayList<>();
}
