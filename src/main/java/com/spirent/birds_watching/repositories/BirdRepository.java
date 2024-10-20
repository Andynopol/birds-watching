package com.spirent.birds_watching.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spirent.birds_watching.entities.Bird;
import org.springframework.stereotype.Repository;

@Repository
public interface BirdRepository extends JpaRepository<Bird, UUID>{ }
