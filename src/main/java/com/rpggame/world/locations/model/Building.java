package com.rpggame.world.locations.model;

import java.util.Collections;
import java.util.List;

import com.rpggame.world.locations.dto.BuildingDTO;

/*
 * Building:
 * The world location building.
 * TODO: add building types (subclasses?)
 * */
public class Building {
    private String locationId;
    private String buildingName;
    private int x;
    private int y;

    private Building(String buildingName, int x, int y, String locationId) {
        this.buildingName = buildingName;
        this.x = x;
        this.y = y;
        this.locationId = locationId;
    }

    public static Building fromDto(BuildingDTO dto, String locationId){
        return new Building(dto.name(), dto.x(), dto.y(), locationId);
    }

    public static List<Building> emptyList() {
        return Collections.emptyList();
    }

    public BuildingDTO toDto() {
        return new BuildingDTO(buildingName, x, y, locationId);
    }
}
