package com.example.Insektorium.database;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;

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
    private float avg_lifespan;
    private float avg_size;
    private float avg_weight;
    private float protein;
    private Date created_at;
}
