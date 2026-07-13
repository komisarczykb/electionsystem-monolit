package me.bartoszkomisarczyk.electionsystem.service.impl;

import me.bartoszkomisarczyk.electionsystem.dto.election.CreateElectionOptionRequest;
import me.bartoszkomisarczyk.electionsystem.dto.election.CreateElectionRequest;
import me.bartoszkomisarczyk.electionsystem.dto.election.ElectionDto;
import me.bartoszkomisarczyk.electionsystem.entity.Election;
import me.bartoszkomisarczyk.electionsystem.exception.ElectionNotFoundException;
import me.bartoszkomisarczyk.electionsystem.repository.ElectionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ElectionServiceImplTest {

    @Mock
    private ElectionRepository electionRepository;

    @InjectMocks
    private ElectionServiceImpl electionService;

    @Test
    void shouldSaveElectionWithOptions() {
        CreateElectionOptionRequest optionRequest = new CreateElectionOptionRequest("Option 1");
        CreateElectionRequest request = new CreateElectionRequest(
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(1),
                "Miss Miedwia",
                List.of(optionRequest)
        );

        when(electionRepository.save(any(Election.class)))
                .thenAnswer(inv -> {
                    Election e = inv.getArgument(0);
                    e.setId(UUID.randomUUID());
                    return e;
                });

        ElectionDto result = electionService.createElection(request);


        assertNotNull(result.id());
        assertEquals("Miss Miedwia", result.title());
        assertEquals(1, result.electionOptionList().size());

        verify(electionRepository).save(any(Election.class));
    }

    @Test
    void shouldSaveElectionWithoutOptions() {
        CreateElectionRequest request = new CreateElectionRequest(
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 31),
                "Missing options",
                List.of()
        );

        when(electionRepository.save(any(Election.class))).thenAnswer(inv -> {
            Election e = inv.getArgument(0);
            e.setId(UUID.randomUUID());
            return e;
        });

        ElectionDto result = electionService.createElection(request);

        assertNotNull(result.id());
        assertEquals("Missing options", result.title());
        assertTrue(result.electionOptionList().isEmpty());
    }

    @Test
    void shouldReturnListWithEntry() {
        Election e1 = Election.builder()
                .id(UUID.randomUUID())
                .title("Elections")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .options(new ArrayList<>())
                .build();

        when(electionRepository.findAll()).thenReturn(List.of(e1));

        List<ElectionDto> result = electionService.getAllElections();

        assertEquals(1, result.size());
    }

    @Test
    void shouldReturnEmptyList() {
        when(electionRepository.findAll()).thenReturn(List.of());

        List<ElectionDto> result = electionService.getAllElections();

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldDeleteElection() {
        UUID electionId = UUID.randomUUID();
        Election election = Election.builder()
                .id(electionId)
                .build();
        when(electionRepository.findById(electionId)).thenReturn(Optional.of(election));

        electionService.deleteElectionById(electionId);

        verify(electionRepository).delete(election);
    }

    @Test
    void shouldNotThrowException() {
        UUID electionId = UUID.randomUUID();
        when(electionRepository.findById(electionId)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> electionService.deleteElectionById(electionId));
        verify(electionRepository, never()).delete(any());
    }

    @Test
    void shouldReturnElectionDto() {
        UUID electionId = UUID.randomUUID();
        Election election = Election.builder()
                .id(electionId)
                .title("Test Election")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .options(List.of())
                .build();
        when(electionRepository.findById(electionId)).thenReturn(Optional.of(election));

        ElectionDto result = electionService.findElectionById(electionId);

        assertEquals(electionId, result.id());
    }

    @Test
    void shouldThrowElectionNotFound() {
        UUID electionId = UUID.randomUUID();
        when(electionRepository.findById(electionId)).thenReturn(Optional.empty());

        assertThrows(ElectionNotFoundException.class,
                () -> electionService.findElectionById(electionId));
    }
}
