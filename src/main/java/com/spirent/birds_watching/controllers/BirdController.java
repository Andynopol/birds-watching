package com.spirent.birds_watching.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.spirent.birds_watching.dto.BirdDto;
import com.spirent.birds_watching.dto.CreateBirdDto;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spirent.birds_watching.services.BirdService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("birds")
public class BirdController {
    
    @Autowired
    private BirdService birdService;

    @GetMapping()
    public ResponseEntity<List<BirdDto>> getBirds(@RequestParam("name") Optional<String> name, @RequestParam("color") Optional<String> color) {
        return ResponseEntity.ok(this.birdService.getBirds(name, color));
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
