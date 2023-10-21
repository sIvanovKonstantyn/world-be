package com.rpggame.world.buildings.model;

import java.util.Objects;

import org.springframework.data.relational.core.mapping.Table;

@Table("USERS")
public class BuildingUser {
    private String id;
    private String locationId;
    private String buildingId;

    public BuildingUser() {
    }

    public static BuildingUser withId(String userId) {
        BuildingUser buildingUser = new BuildingUser();
        buildingUser.id = userId;

        return buildingUser;
    }

    public void updateLocation(Building building) {
        this.locationId = building.getLocationId();
        this.buildingId = building.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuildingUser that = (BuildingUser) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getLocationId() {
        return locationId;
    }

    public String getBuildingId() {
        return buildingId;
    }
}
