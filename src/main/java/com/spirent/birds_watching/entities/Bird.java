package com.spirent.birds_watching.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bird")
public class Bird implements UpdateIfValid {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    
    private String color;
    
    private double weight;

    private double height;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bird", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Sighting> sightings = new ArrayList<>();

    @Override
    public String toString() {
        return "Bird{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", sightings=" + sightings +
                '}';
    }
    
}
