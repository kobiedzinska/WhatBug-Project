package com.example.Insektorium.database.entities.http;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterRequest {

    private String username;
    private String email;
    private String password;

}
