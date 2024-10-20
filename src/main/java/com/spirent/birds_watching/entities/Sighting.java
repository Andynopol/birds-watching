package com.spirent.birds_watching.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sightings")
public class Sighting implements UpdateIfValid {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String location;

    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bird_id")
    private Bird bird;
}
