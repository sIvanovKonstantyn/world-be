package com.rpggame.world.buildings.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rpggame.world.buildings.dto.BuildingDTO;
import com.rpggame.world.buildings.dto.CreateBuildingDTO;
import com.rpggame.world.buildings.model.ShopItem;
import com.rpggame.world.users.model.User;
import com.rpggame.world.users.model.UserItem;
import com.rpggame.world.users.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BuildingServiceTest {

    @Autowired
    private BuildingService buildingService;
    @Autowired
    private UserService userService;

    @Test
    void userEntersTheShopBuysAllItemsAndExitsTheShop() {
        var userId = "user1";
        var locationId = "locationId";
        userService.createUser(userId, locationId);
        buildingService.createBuilding(new CreateBuildingDTO("Shop #1", locationId, "SHOP"));

        User user = userService.findById(userId);

        System.out.println("User current location: " + user.currentLocation());

        System.out.println("User enters the shop...");
        buildingService.enterTheBuilding(userId, new BuildingDTO("Shop #1", locationId));

        user = userService.findById(userId);
        System.out.println("User current location: " + user.currentLocation());

        Set<String> buildingActions = buildingService.getBuildingActions(user.currentBuilding());
        System.out.println("Available building actions: " + buildingActions);

        Map<String, Object> actionDetails = new HashMap<>();
        user.addItems(List.of(UserItem.createNewWithName("Cheap Knife (White)", userId)));
        actionDetails.put("user", user);
        actionDetails.put("items", user.getItems());

        System.out.println("User current items: " + user.getItems());
        System.out.println("User sells all the items into the shop...");
        buildingService.makeBuildingAction(user.currentBuilding(), "SELL ITEMS", actionDetails);
        System.out.println("User current items: " + user.getItems());

        actionDetails.clear();
        buildingService.makeBuildingAction(user.currentBuilding(), "SHOW ITEMS", actionDetails);
        List<ShopItem> shopItems = (List<ShopItem>) actionDetails.get("shopItems");
        System.out.println("Shop items: " + shopItems);

        System.out.println("User exists the shop...");
        buildingService.exitTheBuilding(userId);

        user = userService.findById(userId);
        System.out.println("User current location: " + user.currentLocation());
    }
}