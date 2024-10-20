package com.spirent.birds_watching.dto;

import com.spirent.birds_watching.entities.Bird;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;

@Data
@Getter
@Builder
public class CreateBirdDto {
    @NotEmpty
    private String name;
    private String color;
    private double weight;
    private double height;

    @Override
    public String toString() {
        return "CreateBirdDto{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }

    public static Bird toEntity(CreateBirdDto bird) {
        return Bird.builder()
                .name(bird.getName())
                .color(bird.getColor())
                .weight(bird.getWeight())
                .height(bird.getHeight())
                .sightings(new ArrayList<>())
                .build();
    }
}
