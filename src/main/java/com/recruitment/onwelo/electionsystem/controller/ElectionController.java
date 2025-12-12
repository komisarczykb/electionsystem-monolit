package com.recruitment.onwelo.electionsystem.controller;

import com.recruitment.onwelo.electionsystem.dto.election.CreateElectionOptionRequest;
import com.recruitment.onwelo.electionsystem.dto.election.CreateElectionRequest;
import com.recruitment.onwelo.electionsystem.dto.election.ElectionDto;
import com.recruitment.onwelo.electionsystem.dto.election.ElectionOptionDto;
import com.recruitment.onwelo.electionsystem.dto.vote.ElectionVoteDto;
import com.recruitment.onwelo.electionsystem.dto.vote.VoteCountDto;
import com.recruitment.onwelo.electionsystem.dto.vote.VoteRequest;
import com.recruitment.onwelo.electionsystem.service.ElectionOptionService;
import com.recruitment.onwelo.electionsystem.service.ElectionService;
import com.recruitment.onwelo.electionsystem.service.VoteService;
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
    private final ElectionOptionService optionService;
    private final VoteService voteService;

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

    @PostMapping("/{id}/options")
    public ResponseEntity<ElectionDto> addOptionsToElection(
            @PathVariable UUID id,
            @RequestBody @Valid List<CreateElectionOptionRequest> optionsFromRequest) {
        optionService.addOptionsToElection(id, optionsFromRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(electionService.findElectionById(id));
    }

    @GetMapping("/{id}/options")
    public ResponseEntity<List<ElectionOptionDto>> getOptionsByElectionId(@PathVariable UUID id) {
        return ResponseEntity.ok(optionService.getOptionsByElectionId(id));
    }

    @DeleteMapping("/options/{optionId}")
    public ResponseEntity<Void> deleteOption(@PathVariable UUID optionId) {
        optionService.deleteOption(optionId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/vote")
    public ResponseEntity<Void> vote(@PathVariable("id") UUID electionId, @Valid @RequestBody VoteRequest request) {
        voteService.vote(electionId, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/votes/count")
    public ResponseEntity<VoteCountDto> getVoteCount(@PathVariable("id") UUID electionId) {
        return ResponseEntity.ok(voteService.getVoteCount(electionId));
    }

    @GetMapping("/{id}/votes")
    public ResponseEntity<List<ElectionVoteDto>> getAllVotes(@PathVariable("id") UUID electionId) {
        return ResponseEntity.ok(voteService.getAllVotes(electionId));
    }
}
