package com.example.Insektorium.database.entities.dtos;

import lombok.Getter;

@Getter
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
}
