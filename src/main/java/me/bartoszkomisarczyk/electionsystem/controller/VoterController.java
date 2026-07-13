package me.bartoszkomisarczyk.electionsystem.controller;

import me.bartoszkomisarczyk.electionsystem.dto.voter.CreateVoterRequest;
import me.bartoszkomisarczyk.electionsystem.dto.voter.UpdateVoterStatusRequest;
import me.bartoszkomisarczyk.electionsystem.dto.voter.VoterDto;
import me.bartoszkomisarczyk.electionsystem.service.VoterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/voters/v1")
@RestController
@RequiredArgsConstructor
@Slf4j
public class VoterController {

    private final VoterService voterService;

    @PostMapping
    public ResponseEntity<VoterDto> addNewVoter(@Valid @RequestBody CreateVoterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(voterService.createNewVoter(request));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<VoterDto>> addNewVoters(@Valid @RequestBody List<CreateVoterRequest> requests) {
        //shall be moved to service or not exist at all, created jsut to make testing easier
        List<VoterDto> lista = new LinkedList<>();
        for (CreateVoterRequest req : requests) {
            lista.add(voterService.createNewVoter(req));
        }
        return ResponseEntity.ok(lista);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<VoterDto> changeVoterStatus(@PathVariable UUID id,
                                                      @Valid @RequestBody UpdateVoterStatusRequest request) {
        log.debug(request.toString());
        return ResponseEntity.status(HttpStatus.OK).body(voterService.updateVoterStatus(id, request));
    }

    @GetMapping
    public ResponseEntity<List<VoterDto>> getAllVoters() {
        return ResponseEntity.status(HttpStatus.OK).body(voterService.getAllVoters());
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<List<VoteRequest>> getAllVoterVotes(@PathVariable UUID userID) {
        return ResponseEntity.status(HttpStatus.OK).body()
    }*/
}
