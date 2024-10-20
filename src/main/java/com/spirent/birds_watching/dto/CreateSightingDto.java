package com.spirent.birds_watching.dto;


import com.spirent.birds_watching.entities.Bird;
import com.spirent.birds_watching.entities.Sighting;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
public class CreateSightingDto {
    private String location;
    private LocalDateTime timestamp;

    public static Sighting toEntity(CreateSightingDto sighting, Bird bird) {
        Sighting.builder()
                .timestamp(sighting.getTimestamp())
                .location(sighting.getLocation())
                .bird(bird)
                .build();
    }
}
