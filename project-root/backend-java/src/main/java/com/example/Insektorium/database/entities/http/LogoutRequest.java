package com.example.Insektorium.database.entities.http;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LogoutRequest {
    private String refreshToken;
    private Long clientId;


}
