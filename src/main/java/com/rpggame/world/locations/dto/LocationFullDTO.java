package com.rpggame.world.locations.dto;

import java.util.Collections;
import java.util.List;

public record LocationFullDTO(String name, int x, int y, List<BuildingDTO> buildings) {
    public static LocationFullDTO empty() {
        return new LocationFullDTO(null, Integer.MIN_VALUE, Integer.MIN_VALUE,
            Collections.emptyList());
    }
}
