package com.rpggame.world.users.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table("USERS")
public class User {
    @Id
    private String id;
    private String locationId;
    private String buildingId;

    @MappedCollection(idColumn = "USER_ID")
    private Set<UserItem> items;

    public static User createNew(String userId, String startLocation) {
        User user = new User();
        user.id = userId;
        user.locationId = startLocation;

        return user;
    }

    public String currentLocation() {
        return locationId + (buildingId == null ? "" : "|" + buildingId);
    }

    public String currentBuilding() {
        return buildingId;
    }

    public void addItems(List<UserItem> items) {
        if (this.items == null) {
            this.items = new HashSet<>();
        }
        this.items.addAll(items);
    }

    public Set<UserItem> getItems() {
        return items;
    }

    public void removeItems(Set<UserItem> items) {
        this.items.removeAll(items);
    }

    public void exitTheBuilding() {
        this.buildingId = null;
    }
}
