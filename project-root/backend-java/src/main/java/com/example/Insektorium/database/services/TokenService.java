package com.example.Insektorium.database.services;

import com.example.Insektorium.database.entities.entities.RefreshToken;
import com.example.Insektorium.database.repositories.TokenRepository;
import org.springframework.stereotype.Service;

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
}
