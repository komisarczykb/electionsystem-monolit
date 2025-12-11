package com.recruitment.onwelo.electionsystem.controller;

import com.recruitment.onwelo.electionsystem.dto.election.CreateElectionRequest;
import com.recruitment.onwelo.electionsystem.dto.election.ElectionDto;
import com.recruitment.onwelo.electionsystem.service.ElectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/elections/v1")
@RestController
@RequiredArgsConstructor
public class ElectionController {

    private final ElectionService electionService;

    @PostMapping
    public ResponseEntity<ElectionDto> createElection(@Valid @RequestBody CreateElectionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(electionService.createElection(request));
    }

    @GetMapping
    public ResponseEntity<List<ElectionDto>> getAllElections() {
        return ResponseEntity.ok(electionService.getAllElections());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElectionDto> getSingleElection(@PathVariable UUID id) {
        return ResponseEntity.ok(electionService.findElectionById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteElection(@PathVariable UUID id) {
        electionService.deleteElectionById(id);
        return ResponseEntity.noContent().build();
    }
}
