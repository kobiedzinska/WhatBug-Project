package com.example.Insektorium.database.entities.http;

import lombok.Data;
import lombok.Getter;

import java.util.List;
@Data

public class JwtResponse {
    private String accessToken;
    private String type = "Bearer";
    private String refreshToken;
    private Long clientId;
    private String username;
    private String email;
   // private List<String> roles;

    public JwtResponse(String accessToken, String refreshToken, Long id, String username, String email) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.clientId = id;
        this.username = username;
        this.email = email;
       // this.roles = roles;
    }
}
