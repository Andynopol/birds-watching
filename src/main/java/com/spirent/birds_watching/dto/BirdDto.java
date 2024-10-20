package com.spirent.birds_watching.dto;

import com.spirent.birds_watching.entities.Bird;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class BirdDto {
    private UUID id;
    @NotEmpty
    private String name;
    private String color;
    private Double weight;
    private Double height;

    public static BirdDto fromEntity(Bird bird) {
        return BirdDto.builder()
                .id(bird.getId())
                .name(bird.getName())
                .color(bird.getColor())
                .height(bird.getHeight())
                .weight(bird.getWeight())
                .build();
    }
}
