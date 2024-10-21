package com.spirent.birds_watching.services;

import com.spirent.birds_watching.dto.CreateSightingDto;
import com.spirent.birds_watching.dto.SightingDto;
import com.spirent.birds_watching.dto.UpdateSightingDto;
import com.spirent.birds_watching.entities.Bird;
import com.spirent.birds_watching.entities.Sighting;
import com.spirent.birds_watching.entities.UpdateIfValid;
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

    // Returns a list of Sightings if the bird id provided is valid
    public Optional<List<SightingDto>> findSightingsByBird(UUID birdId){
        var bird = this.birdRepo.findById(birdId);
        if(bird.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(this.sightingRepo.findByBird(bird.get()).stream().map(SightingDto::fromEntity).collect(Collectors.toList()));
    }

    // Creates a sighting if the bird id provided is valid
    public Optional<SightingDto> create(UUID birdId, CreateSightingDto sighting) {

        Optional<Bird> bird = this.birdRepo.findById(birdId);
        if(bird.isEmpty()){
            return Optional.empty();
        }

        if(sighting.getTimestamp() == null) {
            sighting.setTimestamp(LocalDateTime.now());
        }

        return Optional.of(SightingDto.fromEntity(this.sightingRepo.save(CreateSightingDto.toEntity(sighting, bird.get()))));
    }

    // Returns a list of sightings based on the location provided
    public List<SightingDto> findSightingsByLocation(String location){
        return this.sightingRepo.findByLocation(location)
            .stream()
            .map(entity -> SightingDto.fromEntity(entity))
            .collect(Collectors.toList());
    }

    // Returns a list of sightings based on the interval provided
    public List<SightingDto> findSightingsByInterval(LocalDateTime from, LocalDateTime to){
        return this.sightingRepo.findByTimestampBetween(from, to)
            .stream()
            .map(entity -> SightingDto.fromEntity(entity))
            .collect(Collectors.toList());
    }

    // Updates a sighting if it's valid
    public Optional<SightingDto> updateSighting(UUID sightingId, UUID birdId, UpdateSightingDto sighting) {
        Optional<Bird> bird = this.birdRepo.findById(birdId);

        if(bird.isEmpty()){
            return Optional.empty();
        }

        Optional<Sighting> existingSighting = this.sightingRepo.findById(sightingId);
        if(existingSighting.isEmpty()){
            return Optional.empty();
        }

        return existingSighting.map(entity->{
            UpdateIfValid.updateIfValid(entity::setLocation, sighting.getLocation());
            UpdateIfValid.updateIfValid(entity::setTimestamp, sighting.getTimestamp());
            return SightingDto.fromEntity(this.sightingRepo.save(entity));
        });

    }

    // Removes a sighting
    @Transactional
    public Optional<SightingDto> findByIdAndDelete(UUID id) {
        Optional<Sighting> sighting = this.sightingRepo.findById(id);

        if(sighting.isEmpty()){
            return Optional.empty();
        }
        this.sightingRepo.deleteById(id);

        return Optional.of(SightingDto.fromEntity(sighting.get()));
    }

}
