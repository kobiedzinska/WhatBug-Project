package com.example.Insektorium.database.entities.tables;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Entity
@Data
@Table(name = "tokens")
public class RefreshToken {

/*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    */
/*    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "client_id", unique = true)
    private Client client;
    private Instant createdAt;
    private Instant expiresAt;
    @Column(unique = true)
    private String token;
}
