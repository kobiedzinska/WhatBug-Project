package com.example.Insektorium.database.services;

import com.example.Insektorium.database.entities.tables.Bug;
import com.example.Insektorium.database.repositories.BugRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BugServiceTest {

    @Mock
    private BugRepository bugRepository;

    @InjectMocks
    private BugService bugService;

    private Bug testBug;

    @BeforeEach
    void setUp() {
        testBug = new Bug();
        testBug.setId(1L);
        testBug.setName("Ladybug");
    }

    @Test
    void saveBug_ShouldSaveAndReturnBug() {
        // Given
        when(bugRepository.save(testBug)).thenReturn(testBug);

        // When
        Bug result = bugService.saveBug(testBug);

        // Then
        assertEquals(testBug, result);
        verify(bugRepository).save(testBug);
    }

    @Test
    void getAllBugs_ShouldReturnAllBugs() {
        // Given
        List<Bug> bugList = Arrays.asList(testBug);
        when(bugRepository.findAll()).thenReturn(bugList);

        // When
        List<Bug> result = bugService.getAllBugs();

        // Then
        assertEquals(1, result.size());
        assertEquals(testBug, result.get(0));
        verify(bugRepository).findAll();
    }

    @Test
    void deleteBugById_ShouldDeleteBug() {
        // Given
        Long bugId = 1L;

        // When
        bugService.deleteBugById(bugId);

        // Then
        verify(bugRepository).deleteById(bugId);
    }

    @Test
    void deleteBugByObjects_ShouldDeleteBug() {
        // When
        bugService.deleteBugByObjects(testBug);

        // Then
        verify(bugRepository).delete(testBug);
    }

    @Test
    void findBugById_WithExistingId_ShouldReturnBug() {
        // Given
        when(bugRepository.findById(1L)).thenReturn(Optional.of(testBug));

        // When
        Bug result = bugService.findBugById(1L);

        // Then
        assertEquals(testBug, result);
        verify(bugRepository).findById(1L);
    }

    @Test
    void findBugById_WithNonExistingId_ShouldReturnNull() {
        // Given
        when(bugRepository.findById(999L)).thenReturn(Optional.empty());

        // When
        Bug result = bugService.findBugById(999L);

        // Then
        assertNull(result);
        verify(bugRepository).findById(999L);
    }

    @Test
    void findBugByName_ShouldReturnBug() {
        // Given
        when(bugRepository.findBugByName("Ladybug")).thenReturn(testBug);

        // When
        Bug result = bugService.findBugByName("Ladybug");

        // Then
        assertEquals(testBug, result);
        verify(bugRepository).findBugByName("Ladybug");
    }
}
