package com.example.Insektorium.database.entities.dtos;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String mail;
    private String password;
}
