package com.rpggame.world.locations.model;

import java.util.Collections;
import java.util.List;

import com.rpggame.world.locations.dto.LocationBuildingDTO;

/*
 * Building:
 * The world location building.
 * */
public class LocationBuilding {
    private String locationId;
    private String buildingName;
    private int x;
    private int y;

    private LocationBuilding(String buildingName, int x, int y, String locationId) {
        this.buildingName = buildingName;
        this.x = x;
        this.y = y;
        this.locationId = locationId;
    }

    public static LocationBuilding fromDto(LocationBuildingDTO dto, String locationId){
        return new LocationBuilding(dto.name(), dto.x(), dto.y(), locationId);
    }

    public static List<LocationBuilding> emptyList() {
        return Collections.emptyList();
    }

    public LocationBuildingDTO toDto() {
        return new LocationBuildingDTO(buildingName, x, y, locationId);
    }
}
