package com.rpggame.world.users.model;

import java.util.Objects;
import java.util.UUID;

public class UserItem {
    private String id;
    private String name;
    private String userId;

    private UserItem(String id, String name, String userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    public static UserItem createNewWithName(String name, String userId) {
        var id = UUID.randomUUID().toString();
        return new UserItem(id, name, userId);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserItem userItem = (UserItem) o;
        return Objects.equals(id, userItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return name + "|" + id;
    }
}
