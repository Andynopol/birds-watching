package com.spirent.birds_watching.services;

import com.spirent.birds_watching.dto.CreateSightingDto;
import com.spirent.birds_watching.dto.SightingDto;
import com.spirent.birds_watching.repositories.BirdRepository;
import com.spirent.birds_watching.repositories.SightingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SightingService {
    @Autowired
    private SightingRepository sightingRepo;

    @Autowired
    private BirdRepository birdRepo;

    public Optional<List<SightingDto>> findSightingsByBird(UUID birdId){
        var bird = this.birdRepo.findById(birdId);
        if(bird.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(this.sightingRepo.findByBird(bird.get()).stream().map(SightingDto::fromEntity).collect(Collectors.toList()));
    }

    public Optional<SightingDto> create(UUID birdId, CreateSightingDto sighting) {
        var bird = this.birdRepo.findById(birdId);
        if(bird.isEmpty()){
            return Optional.empty();
        }

        if(sighting.getTimestamp() == null) {
            sighting.setTimestamp(LocalDateTime.now());
        }

        return Optional.of(SightingDto.fromEntity(this.sightingRepo.save(CreateSightingDto.toEntity(sighting, bird.get()))));
    }

    @Transactional
    public Optional<SightingDto> findByIdAndDelete(UUID id) {
        var sighting = this.sightingRepo.findById(id);

        if(sighting.isEmpty()){
            return Optional.empty();
        }
        this.sightingRepo.deleteById(id);

        return Optional.of(SightingDto.fromEntity(sighting.get()));
    }


}
