package com.example.Insektorium.security.jwt;

import com.example.Insektorium.database.entities.tables.Client;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SecurityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JwtUtilTest {

    private JwtUtil jwtUtil;
    private Client testClient;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(jwtUtil, "jwtSecret", "mySecretKeyForTestingPurposesOnly1234567890");
        ReflectionTestUtils.setField(jwtUtil, "jwtExpirationMs", 86400000); // 24 hours
        jwtUtil.init();

        testClient = new Client();
        testClient.setId(1L);
        testClient.setUsername("testuser");
    }

    @Test
    void generateToken_ShouldCreateValidToken() {
        // When
        String token = jwtUtil.generateToken(testClient);

        // Then
        assertNotNull(token);
        assertTrue(token.contains("."));
        assertTrue(jwtUtil.validateJwtToken(token));
    }

    @Test
    void getUsernameFromToken_ShouldReturnCorrectUsername() {
        // Given
        String token = jwtUtil.generateToken(testClient);

        // When
        String username = jwtUtil.getUsernameFromToken(token);

        // Then
        assertEquals("testuser", username);
    }

    @Test
    void getIdFromToken_ShouldReturnCorrectId() {
        // Given
        String token = jwtUtil.generateToken(testClient);

        // When
        Long id = jwtUtil.getIdFromToken(token);

        // Then
        assertEquals(1L, id);
    }

    @Test
    void validateJwtToken_WithValidToken_ShouldReturnTrue() {
        // Given
        String token = jwtUtil.generateToken(testClient);

        // When
        boolean isValid = jwtUtil.validateJwtToken(token);

        // Then
        assertTrue(isValid);
    }

    @Test
    void validateJwtToken_WithInvalidToken_ShouldReturnFalse() {
        // Given
        String invalidToken = "invalid.jwt.token";

        // When
        boolean isValid = jwtUtil.validateJwtToken(invalidToken);

        // Then
        assertFalse(isValid);
    }

    @Test
    void validateJwtToken_WithMalformedToken_ShouldReturnFalse() {
        // Given
        String malformedToken = "malformed";

        // When
        boolean isValid = jwtUtil.validateJwtToken(malformedToken);

        // Then
        assertFalse(isValid);
    }

    @Test
    void validateJwtToken_WithExpiredToken_ShouldReturnFalse() {
        // Given - Create util with very short expiration
        JwtUtil shortExpirationUtil = new JwtUtil();
        ReflectionTestUtils.setField(shortExpirationUtil, "jwtSecret", "mySecretKeyForTestingPurposesOnly1234567890");
        ReflectionTestUtils.setField(shortExpirationUtil, "jwtExpirationMs", -1000); // Already expired
        shortExpirationUtil.init();

        String expiredToken = shortExpirationUtil.generateToken(testClient);

        // When
        boolean isValid = jwtUtil.validateJwtToken(expiredToken);

        // Then
        assertFalse(isValid);
    }

    @Test
    void validateJwtToken_WithNullToken_ShouldReturnFalse() {
        // When
        boolean isValid = jwtUtil.validateJwtToken(null);

        // Then
        assertFalse(isValid);
    }

    @Test
    void validateJwtToken_WithEmptyToken_ShouldReturnFalse() {
        // When
        boolean isValid = jwtUtil.validateJwtToken("");

        // Then
        assertFalse(isValid);
    }
}

