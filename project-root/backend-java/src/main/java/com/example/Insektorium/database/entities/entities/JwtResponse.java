package com.example.Insektorium.database.entities.entities;

import lombok.Data;
import lombok.Getter;

import java.util.List;
@Data

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private Long id;
    private String username;
    private String email;
   // private List<String> roles;

    public JwtResponse(String accessToken, String refreshToken, Long id, String username, String email) {
        this.token = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.email = email;
       // this.roles = roles;
    }
}
