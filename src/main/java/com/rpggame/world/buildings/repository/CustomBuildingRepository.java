package com.rpggame.world.buildings.repository;

import com.rpggame.world.buildings.model.Building;

public interface CustomBuildingRepository {
    void updateDependents(Building building);
}
