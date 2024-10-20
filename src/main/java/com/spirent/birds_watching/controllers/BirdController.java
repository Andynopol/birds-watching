package com.spirent.birds_watching.controllers;

import java.util.List;
import java.util.UUID;

import com.spirent.birds_watching.dto.BirdDto;
import com.spirent.birds_watching.dto.CreateBirdDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spirent.birds_watching.entities.Bird;
import com.spirent.birds_watching.services.BirdService;

@RestController
@RequestMapping("birds")
public class BirdController {
    
    @Autowired
    private BirdService birdService;

    @GetMapping()
    public ResponseEntity<List<Bird>> getBirds() {
        return ResponseEntity.ok(this.birdService.getBirds());
    }

    @PostMapping
    public ResponseEntity<?> addBird(@RequestBody @Valid CreateBirdDto bird) {
        return ResponseEntity.ok(this.birdService.create(bird));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateBird(@PathVariable("id") UUID id, @RequestBody @Valid BirdDto bird) {
        return this.birdService.findBirdByIdAndUpdate(id, bird).map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeBird(@PathVariable("id") UUID id) {
        return this.birdService.findBirdByIdAndDelete(id).map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
