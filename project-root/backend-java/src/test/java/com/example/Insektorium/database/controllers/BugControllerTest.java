package com.example.Insektorium.database.controllers;

import com.example.Insektorium.database.entities.tables.Bug;
import com.example.Insektorium.database.entities.tables.BugFound;
import com.example.Insektorium.database.entities.tables.Client;
import com.example.Insektorium.database.repositories.ClientRepository;
import com.example.Insektorium.database.services.BugFoundService;
import com.example.Insektorium.database.services.BugService;
import com.example.Insektorium.database.services.ClientService;
import com.example.Insektorium.security.jwt.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private Client testClient;

    @BeforeEach
    void setUp() {
        testClient = new Client();
        testClient.setId(1L);
        testClient.setUsername("testuser");
        testClient.setEmail("test@example.com");
        testClient.setPassword("encodedPassword");
    }

    @Test
    void getAllUsers_ShouldReturnAllClients() {
        // Given
        List<Client> clients = Arrays.asList(testClient);
        when(clientRepository.findAll()).thenReturn(clients);

        // When
        List<Client> result = clientService.getAllUsers();

        // Then
        assertEquals(1, result.size());
        assertEquals(testClient, result.get(0));
        verify(clientRepository).findAll();
    }

    @Test
    void loginUser_ShouldReturnLoginResult() {
        // Given
        String username = "testuser";
        String password = "password123";
        String expectedResult = "success";
        when(clientRepository.loginUser(username, password)).thenReturn(expectedResult);

        // When
        String result = clientService.loginUser(username, password);

        // Then
        assertEquals(expectedResult, result);
        verify(clientRepository).loginUser(username, password);
    }

    @Test
    void fRegisterUser_ShouldReturnRegistrationId() {
        // Given
        String username = "newuser";
        String email = "new@example.com";
        String password = "encodedPassword";
        Long expectedId = 1L;
        when(clientRepository.fregisterUser(username, email, password)).thenReturn(expectedId);

        // When
        Long result = clientService.fRegisterUser(username, email, password);

        // Then
        assertEquals(expectedId, result);
        verify(clientRepository).fregisterUser(username, email, password);
    }

    @Test
    void floginUser_ShouldReturnClientId() {
        // Given
        String username = "testuser";
        String password = "password123";
        Long expectedId = 1L;
        when(clientRepository.floginUser(username, password)).thenReturn(expectedId);

        // When
        Long result = clientService.floginUser(username, password);

        // Then
        assertEquals(expectedId, result);
        verify(clientRepository).floginUser(username, password);
    }

    @Test
    void addClient_ShouldSaveAndReturnClient() {
        // Given
        when(clientRepository.save(testClient)).thenReturn(testClient);

        // When
        Client result = clientService.addClient(testClient);

        // Then
        assertEquals(testClient, result);
        verify(clientRepository).save(testClient);
    }

    @Test
    void findClientById_WithExistingId_ShouldReturnClient() {
        // Given
        when(clientRepository.findById(1L)).thenReturn(Optional.of(testClient));

        // When
        Client result = clientService.findClientById(1L);

        // Then
        assertEquals(testClient, result);
        verify(clientRepository).findById(1L);
    }

    @Test
    void findClientById_WithNonExistingId_ShouldReturnNull() {
        // Given
        when(clientRepository.findById(999L)).thenReturn(Optional.empty());

        // When
        Client result = clientService.findClientById(999L);

        // Then
        assertNull(result);
        verify(clientRepository).findById(999L);
    }

    @Test
    void findClientByName_ShouldReturnClient() {
        // Given
        when(clientRepository.findClientByUsername("testuser")).thenReturn(testClient);

        // When
        Client result = clientService.findClientByName("testuser");

        // Then
        assertEquals(testClient, result);
        verify(clientRepository).findClientByUsername("testuser");
    }

    @Test
    void findClientByEmail_ShouldReturnClient() {
        // Given
        when(clientRepository.findClientByEmail("test@example.com")).thenReturn(testClient);

        // When
        Client result = clientService.findClientByEmail("test@example.com");

        // Then
        assertEquals(testClient, result);
        verify(clientRepository).findClientByEmail("test@example.com");
    }

    @Test
    void findClientByName_WithNonExistingUser_ShouldReturnNull() {
        // Given
        when(clientRepository.findClientByUsername("nonexistent")).thenReturn(null);

        // When
        Client result = clientService.findClientByName("nonexistent");

        // Then
        assertNull(result);
        verify(clientRepository).findClientByUsername("nonexistent");
    }

    @Test
    void findClientByEmail_WithNonExistingEmail_ShouldReturnNull() {
        // Given
        when(clientRepository.findClientByEmail("nonexistent@example.com")).thenReturn(null);

        // When
        Client result = clientService.findClientByEmail("nonexistent@example.com");

        // Then
        assertNull(result);
        verify(clientRepository).findClientByEmail("nonexistent@example.com");
    }
}

