package com.spirent.birds_watching.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spirent.birds_watching.entities.Bird;
import org.springframework.stereotype.Repository;

@Repository
public interface BirdRepository extends JpaRepository<Bird, UUID>{
    @Query("SELECT bird FROM Bird bird WHERE (:name IS NULL OR bird.name = :name) AND (:color IS NULL OR bird.color = :color)")
    public List<Bird> findByNameAndColor(Optional<String> name, Optional<String> color);
}
