package com.example.Insektorium.database.repositories;

import com.example.Insektorium.database.entities.tables.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Ref;

@Repository
public interface TokenRepository extends JpaRepository<RefreshToken, Long> {
    RefreshToken findRefreshTokenByClient_Id(Long id);
    RefreshToken findRefreshTokenByClient_Email(String mail);
    RefreshToken findRefreshTokenByToken(String token);
}
