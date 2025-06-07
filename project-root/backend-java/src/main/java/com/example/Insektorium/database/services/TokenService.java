package com.example.Insektorium.database.services;

import com.example.Insektorium.database.entities.tables.Client;
import com.example.Insektorium.database.entities.tables.RefreshToken;
import com.example.Insektorium.database.repositories.TokenRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class TokenService {
    TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }
    public RefreshToken saveToken(RefreshToken token) {
        return tokenRepository.save(token);
    }

    public RefreshToken findTokenByMail(String mail) {
        return tokenRepository.findRefreshTokenByClient_Email(mail);
    }
    public RefreshToken findTokenById(Long id) {
        return tokenRepository.findRefreshTokenByClient_Id(id);
    }
    public RefreshToken findTokenByTokenValue(String token){
        return tokenRepository.findRefreshTokenByToken(token);
    }

    public RefreshToken createToken(Client client) {
        RefreshToken refreshToken = new RefreshToken();
        Instant now = Instant.now();
        refreshToken.setClient(client);
        refreshToken.setCreatedAt(now);
        refreshToken.setExpiresAt(now.plus(7, ChronoUnit.DAYS));//7 days
        do {
            refreshToken.setToken(UUID.randomUUID().toString());
        }while(tokenRepository.findRefreshTokenByToken(refreshToken.getToken()) != null);


        return tokenRepository.save(refreshToken);

    }
}
