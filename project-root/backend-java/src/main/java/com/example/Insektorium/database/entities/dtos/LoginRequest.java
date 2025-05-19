package com.example.Insektorium.database.entities.dtos;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String email;
    private String password;
}
