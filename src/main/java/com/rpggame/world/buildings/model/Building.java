package com.rpggame.world.buildings.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.rpggame.world.buildings.dto.CreateBuildingDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

/*
 * Building:
 * The building object. Base class of all the buildings
 * */
public class Building {
    @Id
    private String id;
    private String locationId;
    private String buildingName;
    protected String type;
    @MappedCollection(idColumn = "SHOP_ID")
    protected List<ShopItem> items;

    @MappedCollection(idColumn = "BUILDING_ID")
    private Set<BuildingUser> users;

    protected Building(String id, String buildingName, String locationId, String type,
                       Set<BuildingUser> users) {
        this.id = id;
        this.buildingName = buildingName;
        this.locationId = locationId;
        this.type = type;
        this.users = users;
    }

    public static Building createNewFromDto(CreateBuildingDTO createBuildingDTO) {
        var id = UUID.randomUUID().toString();

        if (BuildingTypes.SHOP.name().equals(createBuildingDTO.buildingType())) {
            return new Shop(id, createBuildingDTO.name(), createBuildingDTO.locationId(),
                new HashSet<>(), new ArrayList<>());
        }

        return new Building(id, createBuildingDTO.name(), createBuildingDTO.locationId(),
            createBuildingDTO.buildingType(), new HashSet<>());
    }

    public void enterUser(String userId) {
        var user = BuildingUser.withId(userId);
        users.add(user);
        user.updateLocation(this);
    }

    String getId() {
        return id;
    }

    String getLocationId() {
        return locationId;
    }

    public Set<BuildingUser> getUsers() {
        return users;
    }

    public Building defineType() {
        if (BuildingTypes.SHOP.name().equals(type)) {
            return new Shop(id, buildingName, locationId, users, getItems());
        }

        return this;
    }

    public List<ShopItem> getItems() {
        return items;
    }

    public Set<String> listOfActions() {
        return Set.of();
    }

    public void makeAction(String action, Map<String, Object> actionDetails) {

    }
}

enum BuildingTypes {
    SHOP
}
