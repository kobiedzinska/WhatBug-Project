package com.example.Insektorium.database.entities.http;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String email;
    private String password;
}
