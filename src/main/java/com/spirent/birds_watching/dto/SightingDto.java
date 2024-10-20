package com.spirent.birds_watching.dto;

import com.spirent.birds_watching.entities.Sighting;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class SightingDto {
    private UUID id;
    private String location;
    private String birdName;
    private LocalDateTime timestamp;

    public static SightingDto fromEntity(Sighting sighting){
        return SightingDto.builder()
                .timestamp(sighting.getTimestamp())
                .location(sighting.getLocation())
                .birdName(sighting.getBird().getName())
                .id(sighting.getId())
                .build();
    }
}
