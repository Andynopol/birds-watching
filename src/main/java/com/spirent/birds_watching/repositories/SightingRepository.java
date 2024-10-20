package com.spirent.birds_watching.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spirent.birds_watching.entities.Bird;
import com.spirent.birds_watching.entities.Sighting;
import java.util.List;
import java.util.Optional;


@Repository
public interface SightingRepository extends JpaRepository<Sighting, UUID>{
    public List<Sighting> findByBird(Bird bird);
}
