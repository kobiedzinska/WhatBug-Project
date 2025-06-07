package com.example.Insektorium.security.jwt;

import com.example.Insektorium.database.entities.tables.Client;
import com.example.Insektorium.database.services.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void loadUserByUsername_WithExistingUser_ShouldReturnUserDetails() {
        // Given
        String username = "testuser";
        String password = "password123";
        Long clientId = 1L;

        Client client = new Client();
        client.setId(clientId);
        client.setUsername(username);
        client.setPassword(password);

        when(clientService.findClientByName(username)).thenReturn(client);

        // When
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        // Then
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().isEmpty());
        verify(clientService).findClientByName(username);
    }

    @Test
    void loadUserByUsername_WithNonExistingUser_ShouldThrowException() {
        // Given
        String username = "nonexistent";
        when(clientService.findClientByName(username)).thenReturn(null);

        // When & Then
        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> customUserDetailsService.loadUserByUsername(username)
        );

        assertEquals("User Not Found with username: " + username, exception.getMessage());
        verify(clientService).findClientByName(username);
    }
}
