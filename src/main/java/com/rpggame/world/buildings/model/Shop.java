package com.rpggame.world.buildings.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rpggame.world.users.model.User;
import com.rpggame.world.users.model.UserItem;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table("BUILDING")
public class Shop extends Building {

    protected Shop(String id, String buildingName, String locationId, Set<BuildingUser> users,
                   List<ShopItem> items) {
        super(id, buildingName, locationId, "SHOP", users);
        this.items = items;
    }
    @Override
    public Set<String> listOfActions() {
        return Set.of("SELL ITEMS", "BUY ITEMS", "SHOW ITEMS");
    }

    @Override
    public void makeAction(String action, Map<String, Object> actionDetails) {
        if ("SELL ITEMS".equals(action)) {
            sellUserItems((Set<UserItem>) actionDetails.get("items"),
                (User) actionDetails.get("user"));
        } else if ("SHOW ITEMS".equals(action)) {
            addItemsIntoDetails(actionDetails);
        }
    }

    private void addItemsIntoDetails(Map<String, Object> actionDetails) {
        actionDetails.put("shopItems", items);
    }

    private void sellUserItems(Set<UserItem> items, User user) {
        this.items.addAll(
            items.stream()
                .map(ui -> ShopItem.createWithName(ui.getName()))
                .toList()
        );
        user.removeItems(items);
    }
}
