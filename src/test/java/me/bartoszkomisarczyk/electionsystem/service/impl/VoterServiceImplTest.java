package me.bartoszkomisarczyk.electionsystem.service.impl;

import me.bartoszkomisarczyk.electionsystem.dto.voter.CreateVoterRequest;
import me.bartoszkomisarczyk.electionsystem.dto.voter.UpdateVoterStatusRequest;
import me.bartoszkomisarczyk.electionsystem.dto.voter.VoterDto;
import me.bartoszkomisarczyk.electionsystem.entity.Voter;
import me.bartoszkomisarczyk.electionsystem.entity.enums.VoterStatus;
import me.bartoszkomisarczyk.electionsystem.exception.VoterNotFoundException;
import me.bartoszkomisarczyk.electionsystem.repository.VoterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VoterServiceImplTest {

    @Mock
    private VoterRepository repository;

    @InjectMocks
    private VoterServiceImpl voterService;

    @Test
    void shouldSaveUserInDb() {
        CreateVoterRequest request = new CreateVoterRequest("Bartosz", "Komisarczyk");
        UUID generatedId = UUID.randomUUID();
        Voter savedVoter = Voter.builder()
                .id(generatedId)
                .firstName("Bartosz")
                .lastName("Komisarczyk")
                .build();

        when(repository.save(any(Voter.class))).thenReturn(savedVoter);

        VoterDto result = voterService.createNewVoter(request);

        assertEquals(generatedId, result.id());
        assertEquals("Bartosz", result.firstName());
        assertEquals("Komisarczyk", result.lastName());
        assertEquals(VoterStatus.ACTIVE, result.status());
    }

    @Test
    void shouldChangeVoterStatusToBlocked() {
        UUID voterId = UUID.randomUUID();
        UpdateVoterStatusRequest request = new UpdateVoterStatusRequest(VoterStatus.BLOCKED);
        Voter existingVoter = Voter.builder()
                .id(voterId)
                .firstName("Bartosz")
                .lastName("Komisarczyk")
                .build();

        when(repository.findById(voterId)).thenReturn(Optional.of(existingVoter));
        when(repository.save(any(Voter.class))).thenAnswer(inv -> inv.getArgument(0));

        VoterDto result = voterService.updateVoterStatus(voterId, request);

        assertEquals(VoterStatus.BLOCKED, result.status());
    }

    @Test
    void shouldThrowVoterNotFoundException() {
        UUID voterId = UUID.randomUUID();
        UpdateVoterStatusRequest request = new UpdateVoterStatusRequest(VoterStatus.BLOCKED);
        when(repository.findById(voterId)).thenReturn(Optional.empty());

        assertThrows(VoterNotFoundException.class,
                () -> voterService.updateVoterStatus(voterId, request));
    }

    @Test
    void shouldReturnListOfVotersDto() {
        Voter v1 = Voter.builder()
                .id(UUID.randomUUID())
                .firstName("Bartosz")
                .lastName("Komisarczyk")
                .status(VoterStatus.ACTIVE)
                .build();
        Voter v2 = Voter.builder()
                .id(UUID.randomUUID())
                .firstName("Bartlomiej")
                .lastName("Komis")
                .status(VoterStatus.BLOCKED)
                .build();

        when(repository.findAll()).thenReturn(new ArrayList<>(List.of(v1, v2)));
        List<VoterDto> result = voterService.getAllVoters();

        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnEmptyList() {
        when(repository.findAll()).thenReturn(new ArrayList<>());

        List<VoterDto> result = voterService.getAllVoters();

        assertTrue(result.isEmpty());
    }
}
