package com.example.Insektorium.database.services;

import com.example.Insektorium.database.entities.tables.BugFound;
import com.example.Insektorium.database.repositories.BugFoundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BugFoundServiceTest {

    @Mock
    private BugFoundRepository bugFoundRepository;

    @InjectMocks
    private BugFoundService bugFoundService;

    private BugFound testBugFound;

    @BeforeEach
    void setUp() {
        testBugFound = new BugFound();
        testBugFound.setId(1L);
        testBugFound.setLatitude(50.0);
        testBugFound.setLongitude(20.0);
    }

    @Test
    void getAllBugFound_ShouldReturnAllBugFound() {
        // Given
        List<BugFound> bugList = Arrays.asList(testBugFound);
        when(bugFoundRepository.findAll()).thenReturn(bugList);

        // When
        List<BugFound> result = bugFoundService.getAllBugFound();

        // Then
        assertEquals(1, result.size());
        assertEquals(testBugFound, result.get(0));
        verify(bugFoundRepository).findAll();
    }

    @Test
    void getAllByClient_Id_ShouldReturnClientBugs() {
        // Given
        Long clientId = 1L;
        List<BugFound> bugList = Arrays.asList(testBugFound);
        when(bugFoundRepository.findAllByClient_IdOrderByName(clientId)).thenReturn(bugList);

        // When
        List<BugFound> result = bugFoundService.getAllByClient_Id(clientId);

        // Then
        assertEquals(1, result.size());
        assertEquals(testBugFound, result.get(0));
        verify(bugFoundRepository).findAllByClient_IdOrderByName(clientId);
    }

    @Test
    void saveBug_ShouldSaveAndReturnBugFound() {
        // Given
        when(bugFoundRepository.save(testBugFound)).thenReturn(testBugFound);

        // When
        BugFound result = bugFoundService.saveBug(testBugFound);

        // Then
        assertEquals(testBugFound, result);
        verify(bugFoundRepository).save(testBugFound);
    }
}
