package com.recruitment.onwelo.electionsystem.controller;

import com.recruitment.onwelo.electionsystem.dto.election.CreateElectionRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/elections/v1")
@RestController
public class ElectionController {

    @PostMapping
    public ResponseEntity<?> createNewElection(@Valid @RequestBody CreateElectionRequest electionCreationRequest)
}
