package com.spirent.birds_watching.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spirent.birds_watching.entities.Bird;
import java.util.Optional;


public interface BirdRepository extends JpaRepository<Bird, UUID>{
    public Optional<Bird> findById(UUID id);
}
