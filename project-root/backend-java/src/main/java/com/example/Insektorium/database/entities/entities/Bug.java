package com.example.Insektorium.database.entities.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Entity
@Data
@Table(name = "Bugs")
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phylum;
    private String subphylum;
    private String class_;
    private String order_;
    private String family;
    private String genus;
    private String species;
    private String subspecies;
    private String description;
    private String imageURL;
    private Float avg_lifespan;
    private Float avg_size;
    private Float avg_weight;
    private Float protein;
    private Date created_at;
}
