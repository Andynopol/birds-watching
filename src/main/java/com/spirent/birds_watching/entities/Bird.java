package com.spirent.birds_watching.entities;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Bird {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    
    private String color;
    
    private double weight;

    private double height;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Sighting> sightings;

    public static <T> void updateIfValid(Consumer<T> setter, T value) {
        if(value != null) {
            setter.accept(value);
        }
    }
    
}
