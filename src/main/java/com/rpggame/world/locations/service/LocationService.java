package com.rpggame.world.locations.service;

import java.util.List;
import java.util.Map;

import com.rpggame.world.locations.dto.LocationBuildingDTO;
import com.rpggame.world.locations.dto.CityLocationDTO;
import com.rpggame.world.locations.dto.LocationFullDTO;
import com.rpggame.world.locations.model.Location;
import com.rpggame.world.locations.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public void createNewCity(Map<CityLocationDTO, List<LocationBuildingDTO>> cityBuildings) {
        cityBuildings.forEach(
            (locationData, buildingData) -> {
                Location cityLocation;
                if (locationData.regionName().equals("DIGGERS")) {
                    cityLocation = Location.createNewDiggersCityLocation(
                        locationData.name(),
                        locationData.x(),
                        locationData.y(),
                        buildingData
                    );
                } else {
                    throw new IllegalArgumentException("Wrong region name");
                }

                locationRepository.insert(cityLocation);
            }
        );
    }

    public LocationFullDTO findByCoordinates(int x, int y) {
        return locationRepository.findByCoordinates(x, y)
            .map(Location::toDto)
            .orElse(LocationFullDTO.empty());
    }

    public void createNewEmptyLocation(int x, int y, int level, String region) {
        if (region.equals("DIGGERS")) {
            var location = Location.createNewEmptyDiggersLocation(
                x,
                y,
                level
            );
            locationRepository.insert(location);
        } else {
            throw new IllegalArgumentException("Wrong region name");
        }
    }
}
