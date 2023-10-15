package com.rpggame.world.locations.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import com.rpggame.world.locations.dto.BuildingDTO;
import com.rpggame.world.locations.dto.CityLocationDTO;
import com.rpggame.world.locations.dto.LocationFullDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LocationServiceITest {

    @Autowired
    private LocationService service;

    @Test
    void createNewCityAndFindItByCoordinates() {
        int x = 0;
        int y = 0;

        createCity(x, y);
        var cityDto = service.findByCoordinates(x, y);

        assertEquals(cityDto.x(), x);
        assertEquals(cityDto.y(), y);
        assertEquals(cityDto.buildings().size(), 3);
    }

    @Test
    void create10x10WorldWithTwoCitiesAndWalkFromOneToAnother() {
        int x = 0;
        int y = 0;
        int xFinish = 9;
        int yFinish = 9;

        //world generation
        generateWorld(x, y, xFinish, yFinish);

        //walking
        System.out.printf("USER1 starts in location: %s/%s%n", x, y);

        while (x <= xFinish) {
            var locationDTO = service.findByCoordinates(x, y);
            System.out.printf("Location name: %s%n", locationDTO.name());
            if (!locationDTO.buildings().isEmpty()) {
                System.out.printf("Buildings on location: %s%n", locationDTO.buildings());
            } else {
                System.out.println("Location is empty");
            }

            x++;
            y++;
        }

        assertEquals("Test city", service.findByCoordinates(x-1, y-1).name());
    }

    private void generateWorld(int xStart, int yStart, int xFinish, int yFinish) {
        createCity(xStart, yStart);
        createCity(xFinish, yFinish);

        for (int i = xStart + 1; i < xFinish; i++) {
            for (int j = yStart + 1; j < yFinish; j++) {
                createEmptyLocation(i, j, i);
            }
        }
    }

    private void createEmptyLocation(int x, int y, int level) {
        service.createNewEmptyLocation(x, y, level, "DIGGERS");
    }

    private void createCity(int x, int y) {
        Map<CityLocationDTO, List<BuildingDTO>> cityBuildings = new HashMap<>();
        cityBuildings.put(
            new CityLocationDTO("Test city", 0, "DIGGERS", x, y),
            List.of(
                new BuildingDTO("Shop", 10, 10, null),
                new BuildingDTO("Hospital", 20, 10, null),
                new BuildingDTO("House #1", 30, 10, null)
            )
        );

        service.createNewCity(cityBuildings);
    }
}