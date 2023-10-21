package com.rpggame.world.buildings.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;

public class ShopItem {
    @Id
    private String id;
    private String name;

    private ShopItem(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ShopItem createWithName(String name) {
        var id = UUID.randomUUID().toString();
        return new ShopItem(id, name);
    }

    @Override
    public String toString() {
        return name + "|" + id;
    }
}
