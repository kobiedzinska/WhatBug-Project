package com.example.Insektorium.database.entities.tables;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.locationtech.jts.geom.Point;

import java.util.Date;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Data
@Table(name = "bugs_found")
public class BugFound {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name = "N/A";                                                                                            // nazwa własna użytkownika, użytkownik będzie mógł nazywać znalezione robaki

    @Column(columnDefinition = "POINT", nullable = false)
    private Point location;                                                                                             // do przechowywania współrzędnych geograficznych. Funkcje do tworzenia w database/geometryUtils

    private double latitude;                                                                                                        // narazie je zostawiam
    private double longitude;

    @Column(name = "created_at", columnDefinition = "timestamp default now()")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "bug_id", nullable = false)
    private Bug bug;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Lob
    @Column(name = "image", columnDefinition = "MEDIUMBLOB")
    private byte[] image;

    @Override
    public String toString() {
        return "BugFound{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longtitude=" + longitude +
                ", created_at=" + createdAt +
                '}';
    }
}
