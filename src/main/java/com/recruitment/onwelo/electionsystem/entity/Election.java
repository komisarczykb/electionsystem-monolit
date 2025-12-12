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
    @Column(nullable = false)
    private String title;
    @NotNull(message = "startDate cannot be null")
    @Column(nullable = false)
    private LocalDate startDate;
    @NotNull(message = "endDate cannot be null")
    @Column(nullable = false)
    private LocalDate endDate;

    @Builder.Default
    @OneToMany(mappedBy = "election",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    List<ElectionOption> options = new ArrayList<>();

    public void addOption(ElectionOption option) {
        option.setElection(this);
        options.add(option);
    }

    public void addOption(List<ElectionOption> options) {
        options.forEach(this::addOption);
    }
}
