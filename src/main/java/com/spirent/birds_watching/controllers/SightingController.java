package com.spirent.birds_watching.controllers;

import com.spirent.birds_watching.dto.CreateSightingDto;
import com.spirent.birds_watching.dto.GetSightingsByIntervalDto;
import com.spirent.birds_watching.dto.GetSightingsByLocationDto;
import com.spirent.birds_watching.dto.SightingDto;
import com.spirent.birds_watching.dto.UpdateSightingDto;
import com.spirent.birds_watching.services.SightingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/sightings")
public class SightingController {

    @Autowired
    private SightingService sightingService;

    @GetMapping("/bird/{birdId}")
    public ResponseEntity<?> getSightingsByBird(@PathVariable("birdId") UUID birdId) {
        return this.sightingService.findSightingsByBird(birdId).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/bird/{birdId}")
    public ResponseEntity<?> addSighting(@PathVariable("birdId") UUID birdId, @RequestBody @Valid CreateSightingDto sighting) {
        return this.sightingService.create(birdId, sighting).map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/location")
    public ResponseEntity<List<SightingDto>> getSightingsByLocation(@RequestBody @Valid GetSightingsByLocationDto body) {
        return ResponseEntity.ok(this.sightingService.findSightingsByLocation(body.getLocation()));
    }

    @PostMapping("/interval")
    public ResponseEntity<List<SightingDto>> getSightingsByInterval(@RequestBody @Valid GetSightingsByIntervalDto body) {
        return ResponseEntity.ok(this.sightingService.findSightingsByInterval(body.getFrom(), body.getTo()));
    }
    

    @PatchMapping("/bird/{birdId}/{sightingId}")
    public ResponseEntity<?> updateSighting(@PathVariable("birdId") UUID birdId, @PathVariable("sightingId") UUID sightingId, @RequestBody UpdateSightingDto sighting) {
        return this.sightingService.updateSighting(sightingId, birdId, sighting).map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeSighting(@PathVariable("id") UUID id) {
        return this.sightingService.findByIdAndDelete(id).map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
