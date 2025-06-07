package com.example.Insektorium.database.entities.http;

import lombok.Getter;

@Getter
public class LogoutRequest {
    private String token;
    private Long id;

}
