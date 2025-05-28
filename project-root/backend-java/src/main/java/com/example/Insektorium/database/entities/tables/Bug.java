package com.example.Insektorium.database.entities.tables;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;

import java.util.Date;

@NoArgsConstructor
@Entity
@Data
@Table(name = "bugs")
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 20)
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

    @Column(name = "avg_lifespan")
    private Float avgLifespan;
    @Column(name = "avg_size")
    private Float avgSize;
    @Column(name = "avg_weight")
    private Float avgWeight;
    private Float protein;


    @Column(columnDefinition = "BLOB")
    private String imageBit;
    @Column(length = 255)
    private String description;
}
