package com.example.Insektorium.database.entities.dtos;

import com.example.Insektorium.database.entities.entities.Client;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class RefreshTokenDTO {
    private Long id;
    private String username;
    private Date createdAt;
    private Date expiresAt;
}