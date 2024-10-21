package com.spirent.birds_watching.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class GetSightingsByLocationDto {
    private String location;
}
