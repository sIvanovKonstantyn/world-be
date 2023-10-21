package com.rpggame.world.buildings.service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.rpggame.world.buildings.dto.BuildingDTO;
import com.rpggame.world.buildings.dto.CreateBuildingDTO;
import com.rpggame.world.buildings.model.Building;
import com.rpggame.world.buildings.repository.BuildingRepository;
import com.rpggame.world.users.model.User;
import com.rpggame.world.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    public void createBuilding(CreateBuildingDTO createBuildingDTO) {
        Building building = Building.createNewFromDto(createBuildingDTO);
        buildingRepository.insert(building);
    }

    public void enterTheBuilding(String userId, BuildingDTO buildingDTO) {
        buildingRepository.findByNameAndLocation(buildingDTO.name(), buildingDTO.locationId())
            .ifPresent(building -> {
                building.enterUser(userId);
                buildingRepository.updateDependents(building);
            });
    }

    public Set<String> getBuildingActions(String buildingId) {
        return getBuilding(buildingId)
            .map(Building::listOfActions)
            .orElse(Set.of());
    }

    private Optional<Building> getBuilding(String buildingId) {
        return buildingRepository.findById(buildingId)
            .map(Building::defineType);
    }

    public void makeBuildingAction(String buildingId, String action,
                                   Map<String, Object> actionDetails) {
        getBuilding(buildingId)
            .ifPresent(
                b -> {
                    b.makeAction(action, actionDetails);
                    buildingRepository.save(b);
                    if (actionDetails.containsKey("user")) {
                        userRepository.save((User)actionDetails.get("user"));
                    }
                }
            );
    }

    public void exitTheBuilding(String userId) {
        userRepository.findById(userId)
            .ifPresent(
               u -> {
                   u.exitTheBuilding();
                   userRepository.save(u);
               }
            );
    }
}
