package com.recruitment.onwelo.electionsystem.controller;

import com.recruitment.onwelo.electionsystem.dto.CreateVoterRequest;
import com.recruitment.onwelo.electionsystem.dto.UpdateVoterStatusRequest;
import com.recruitment.onwelo.electionsystem.service.VoterDto;
import com.recruitment.onwelo.electionsystem.service.VoterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/voters/v1")
@RestController
public class VoterController {

    private final VoterService voterService;

    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }

    @PostMapping
    public ResponseEntity<VoterDto> addNewVoter(@Validated @RequestBody CreateVoterRequest request) {
        VoterDto createdVoterDto = voterService.createNewVoter(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVoterDto);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<VoterDto> changeVoterStatus(@PathVariable UUID id, @Validated @RequestBody UpdateVoterStatusRequest request) {
        VoterDto updatedVoterDto = voterService.updateVoterStatus(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(updatedVoterDto);
    }
}
