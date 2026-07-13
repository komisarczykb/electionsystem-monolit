package me.bartoszkomisarczyk.electionsystem.controller;

import me.bartoszkomisarczyk.electionsystem.dto.election.CreateElectionOptionRequest;
import me.bartoszkomisarczyk.electionsystem.dto.election.CreateElectionRequest;
import me.bartoszkomisarczyk.electionsystem.dto.election.ElectionDto;
import me.bartoszkomisarczyk.electionsystem.dto.election.ElectionOptionDto;
import me.bartoszkomisarczyk.electionsystem.dto.vote.ElectionVoteDto;
import me.bartoszkomisarczyk.electionsystem.dto.vote.VoteCountDto;
import me.bartoszkomisarczyk.electionsystem.dto.vote.VoteRequest;
import me.bartoszkomisarczyk.electionsystem.service.ElectionOptionService;
import me.bartoszkomisarczyk.electionsystem.service.ElectionService;
import me.bartoszkomisarczyk.electionsystem.service.VoteService;
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
