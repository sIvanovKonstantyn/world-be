package com.rpggame.world.locations.repository;

import java.util.Optional;

import com.rpggame.world.locations.model.Location;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, String>, CustomLocationInsert {
    @Query("SELECT * FROM location l WHERE l.x = :x AND l.y = :y")
    Optional<Location> findByCoordinates(@Param("x") int x, @Param("y") int y);
}
