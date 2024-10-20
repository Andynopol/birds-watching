package com.spirent.birds_watching.repositories;

import java.util.UUID;

import com.spirent.birds_watching.dto.BirdDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spirent.birds_watching.entities.Bird;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BirdRepository extends JpaRepository<Bird, UUID>{
    public Optional<Bird> findById(@NotNull UUID id);
    public List<Bird> findAllBirds();
    public Optional<Bird> findByIdAndUpdate(UUID id, BirdDto update);
    public Optional<Bird> findByIdAndDelete(UUID id);
}
