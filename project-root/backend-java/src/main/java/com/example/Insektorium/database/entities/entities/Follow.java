package com.example.Insektorium.database.entities.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Data
@Table(name = "Follows")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private Client follower; // Użytkownik, który obserwuje

    @ManyToOne
    @JoinColumn(name = "followed_id", nullable = false)
    private Client followed; // Użytkownik, który jest obserwowany

    private Timestamp created_at;
}
