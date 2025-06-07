package com.example.Insektorium.database.services;

import com.example.Insektorium.database.entities.tables.Client;
import com.example.Insektorium.database.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
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
    void getAllUsers_WithEmptyDatabase_ShouldReturnEmptyList() {
        // Given
        when(clientRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        List<Client> result = clientService.getAllUsers();

        // Then
        assertTrue(result.isEmpty());
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
    void loginUser_WithInvalidCredentials_ShouldReturnFailure() {
        // Given
        String username = "testuser";
        String password = "wrongpassword";
        String expectedResult = "failure";
        when(clientRepository.loginUser(username, password)).thenReturn(expectedResult);

        // When
        String result = clientService.loginUser(username, password);

        // Then
        assertEquals(expectedResult, result);
        verify(clientRepository).loginUser(username, password);
    }

    @Test
    void loginUser_WithNullUsername_ShouldHandleGracefully() {
        // Given
        when(clientRepository.loginUser(null, "password")).thenReturn("failure");

        // When
        String result = clientService.loginUser(null, "password");

        // Then
        assertEquals("failure", result);
        verify(clientRepository).loginUser(null, "password");
    }

    @Test
    void fRegisterUser_WithValidData_ShouldReturnRegistrationId() {
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
    void fRegisterUser_WithExistingUser_ShouldReturnZero() {
        // Given
        String username = "existinguser";
        String email = "existing@example.com";
        String password = "encodedPassword";
        when(clientRepository.fregisterUser(username, email, password)).thenReturn(0L);

        // When
        Long result = clientService.fRegisterUser(username, email, password);

        // Then
        assertEquals(0L, result);
        verify(clientRepository).fregisterUser(username, email, password);
    }

    @Test
    void fRegisterUser_WithNullValues_ShouldHandleGracefully() {
        // Given
        when(clientRepository.fregisterUser(null, null, null)).thenReturn(0L);

        // When
        Long result = clientService.fRegisterUser(null, null, null);

        // Then
        assertEquals(0L, result);
        verify(clientRepository).fregisterUser(null, null, null);
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
    void floginUser_WithInvalidCredentials_ShouldReturnNull() {
        // Given
        String username = "testuser";
        String password = "wrongpassword";
        when(clientRepository.floginUser(username, password)).thenReturn(null);

        // When
        Long result = clientService.floginUser(username, password);

        // Then
        assertNull(result);
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
    void addClient_WithNullClient_ShouldHandleGracefully() {
        // Given
        when(clientRepository.save(null)).thenReturn(null);

        // When
        Client result = clientService.addClient(null);

        // Then
        assertNull(result);
        verify(clientRepository).save(null);
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
    void findClientById_WithNullId_ShouldReturnNull() {
        // Given
        when(clientRepository.findById(null)).thenReturn(Optional.empty());

        // When
        Client result = clientService.findClientById(null);

        // Then
        assertNull(result);
        verify(clientRepository).findById(null);
    }

    @Test
    void findClientByName_WithExistingUsername_ShouldReturnClient() {
        // Given
        when(clientRepository.findClientByUsername("testuser")).thenReturn(testClient);

        // When
        Client result = clientService.findClientByName("testuser");

        // Then
        assertEquals(testClient, result);
        verify(clientRepository).findClientByUsername("testuser");
    }

    @Test
    void findClientByName_WithNonExistingUsername_ShouldReturnNull() {
        // Given
        when(clientRepository.findClientByUsername("nonexistent")).thenReturn(null);

        // When
        Client result = clientService.findClientByName("nonexistent");

        // Then
        assertNull(result);
        verify(clientRepository).findClientByUsername("nonexistent");
    }

    @Test
    void findClientByName_WithNullUsername_ShouldReturnNull() {
        // Given
        when(clientRepository.findClientByUsername(null)).thenReturn(null);

        // When
        Client result = clientService.findClientByName(null);

        // Then
        assertNull(result);
        verify(clientRepository).findClientByUsername(null);
    }

    @Test
    void findClientByName_WithEmptyUsername_ShouldReturnNull() {
        // Given
        when(clientRepository.findClientByUsername("")).thenReturn(null);

        // When
        Client result = clientService.findClientByName("");

        // Then
        assertNull(result);
        verify(clientRepository).findClientByUsername("");
    }

    @Test
    void findClientByEmail_WithExistingEmail_ShouldReturnClient() {
        // Given
        when(clientRepository.findClientByEmail("test@example.com")).thenReturn(testClient);

        // When
        Client result = clientService.findClientByEmail("test@example.com");

        // Then
        assertEquals(testClient, result);
        verify(clientRepository).findClientByEmail("test@example.com");
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

    @Test
    void findClientByEmail_WithNullEmail_ShouldReturnNull() {
        // Given
        when(clientRepository.findClientByEmail(null)).thenReturn(null);

        // When
        Client result = clientService.findClientByEmail(null);

        // Then
        assertNull(result);
        verify(clientRepository).findClientByEmail(null);
    }

    @Test
    void findClientByEmail_WithInvalidEmailFormat_ShouldReturnNull() {
        // Given
        String invalidEmail = "invalid-email-format";
        when(clientRepository.findClientByEmail(invalidEmail)).thenReturn(null);

        // When
        Client result = clientService.findClientByEmail(invalidEmail);

        // Then
        assertNull(result);
        verify(clientRepository).findClientByEmail(invalidEmail);
    }

    @Test
    void findClientByEmail_WithEmptyEmail_ShouldReturnNull() {
        // Given
        when(clientRepository.findClientByEmail("")).thenReturn(null);

        // When
        Client result = clientService.findClientByEmail("");

        // Then
        assertNull(result);
        verify(clientRepository).findClientByEmail("");
    }
}
