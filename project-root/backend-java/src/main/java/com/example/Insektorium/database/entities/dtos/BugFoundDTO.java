package com.example.Insektorium.database.entities.dtos;

import com.example.Insektorium.database.entities.tables.Bug;
import com.example.Insektorium.database.entities.tables.Client;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;
@NoArgsConstructor
@Data
public class BugFoundDTO {
    private String name;//bug name to search
    private Point location;
    private double latitude;
    private double longitude;
}




