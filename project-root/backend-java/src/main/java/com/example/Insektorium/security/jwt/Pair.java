package com.example.Insektorium.security.jwt;

import lombok.Data;

import java.util.Date;

@Data
public class Pair {
    private String token;
    private Date now;

    public Pair(String token, Date now) {
        this.token = token;
        this.now = now;
    }
}
