package com.spirent.birds_watching.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.spirent.birds_watching.dto.BirdDto;
import com.spirent.birds_watching.dto.CreateBirdDto;
import com.spirent.birds_watching.entities.UpdateIfValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spirent.birds_watching.entities.Bird;
import com.spirent.birds_watching.repositories.BirdRepository;

@Service
public class BirdService {
    @Autowired
    private BirdRepository birdRepo;


    public List<Bird> getBirds() {
        return this.birdRepo.findAll();
    }

    public BirdDto create(CreateBirdDto birdDto){
        Bird bird = birdRepo.save(CreateBirdDto.toEntity(birdDto));
        return BirdDto.fromEntity(bird);
    }

    public Optional<BirdDto> findBirdByIdAndUpdate(UUID id, BirdDto bird) {
        Optional<Bird> existingBird = this.birdRepo.findById(id);
        if(existingBird.isEmpty()){
            return Optional.empty();
        }
        return existingBird.map(entity -> {
            UpdateIfValid.updateIfValid(entity::setName, bird.getName());
            UpdateIfValid.updateIfValid(entity::setColor, bird.getColor());
            UpdateIfValid.updateIfValid(entity::setWeight, bird.getWeight());
            UpdateIfValid.updateIfValid(entity::setHeight, bird.getHeight());
            return BirdDto.fromEntity(this.birdRepo.save(entity));
        });
    }

    public Optional<BirdDto> findBirdByIdAndDelete(UUID id) {
        Optional<Bird> bird = this.birdRepo.findById(id);
        if (bird.isEmpty()) {
            return Optional.empty();
        }
        this.birdRepo.deleteById(id);
        return Optional.of(BirdDto.fromEntity(bird.get()));
    }
}
