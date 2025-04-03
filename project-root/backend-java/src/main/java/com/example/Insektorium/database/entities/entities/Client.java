package com.example.Insektorium.database.entities.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 225, nullable = false, unique = true)
    private String email;
    @Column(length = 35, nullable = false, unique = true)
    private String username;
    @Column(length = 225, nullable = false) // Hasło będzie haszowane SHA2
    private String password;
    @Column(length = 5)
    private String role = "demo";
    @Column(name = "created_at", columnDefinition = "timestamp default now()")
    private LocalDateTime createdAt;
    @Column(length = 7, nullable = false)
    private String status;  // będzie pokazywać, czy konto jest zablokowane czy odblokowane. Odblokowujemy przy użyciu email, ochrona przy logowaniu się na konto. 5 prób logowań.


    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BugFound> foundBugs;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoginAttempt> loginAttempts;

   // @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
   // private List<Follow> followers;

   // @OneToMany(mappedBy = "followed", cascade = CascadeType.ALL)
   // private List<Follow> following;


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", created_at=" + createdAt +
                '}';
    }
}
