package com.spirent.birds_watching.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spirent.birds_watching.entities.Bird;
import com.spirent.birds_watching.entities.Sighting;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface SightingRepository extends JpaRepository<Sighting, UUID>{
    public List<Sighting> findByBird(Bird bird);
    public List<Sighting> findByLocation(String location);
    public List<Sighting> findByTimestampBetween(LocalDateTime from, LocalDateTime to);
}
