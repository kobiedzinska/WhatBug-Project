package com.example.Insektorium.database.repositories;

import com.example.Insektorium.database.entities.entities.Client;
import com.example.Insektorium.database.entities.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface TokenRepository extends JpaRepository<RefreshToken, Long> {
    public RefreshToken findRefreshTokenByClient_Id(Long id);
    public RefreshToken findRefreshTokenByClient_Email(String mail);
}
