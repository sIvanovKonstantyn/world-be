package com.rpggame.world.buildings.repository.impl;

import com.rpggame.world.buildings.model.Building;
import com.rpggame.world.buildings.repository.CustomBuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomBuildingRepositoryImpl implements CustomBuildingRepository {

    @Autowired
    private JdbcTemplate template;
    @Override
    public void updateDependents(Building building) {
        building.getUsers()
                .forEach(
                    u -> template.update(
                        "UPDATE USERS SET location_id = ?, building_id = ?",
                        u.getLocationId(),
                        u.getBuildingId()
                    )
                );
    }
}
