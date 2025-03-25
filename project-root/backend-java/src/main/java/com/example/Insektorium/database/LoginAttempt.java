package com.example.Insektorium.database;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Entity
@Data
@Table(name = "Login_Attempts")
public class LoginAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date last_attempt;
    private boolean success;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;
}
