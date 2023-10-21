package com.rpggame.world.buildings.repository;

import java.util.Optional;

import com.rpggame.world.buildings.model.Building;
import com.rpggame.world.common.repository.CustomInsert;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends CrudRepository<Building, String>,
    CustomInsert<Building>, CustomBuildingRepository {
    @Query("SELECT * FROM building b WHERE b.building_name = :name AND b.location_id = :locationId")
    Optional<Building> findByNameAndLocation(@Param("name") String name, @Param("locationId") String locationId);
}
