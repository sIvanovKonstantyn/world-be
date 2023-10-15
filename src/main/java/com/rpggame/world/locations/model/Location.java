package com.rpggame.world.locations.model;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.rpggame.world.locations.dto.BuildingDTO;
import com.rpggame.world.locations.dto.LocationFullDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.MappedCollection;

/*
 * Location:
 * The world location. Contains building, monster groups.
 * All the locations have levels, Monster level depends on location level
 * All the locations are belong to some region. Monster levels and types also may very based on region
 * */
public class Location {
    @Id
    private String id;
    private String locationName;

    private int level;

    private Region region;
    private int x;
    private int y;

    @MappedCollection(idColumn = "LOCATION_ID")
    private List<Building> buildings;

    @MappedCollection(idColumn = "LOCATION_ID")
    private MonsterGroup monsterGroup;

    private Location(String id, String locationName, int level, Region region, int x, int y,
                     List<Building> buildings, MonsterGroup monsterGroup) {
        this.id = id;
        this.locationName = locationName;
        this.level = level;
        this.region = region;
        this.x = x;
        this.y = y;
        this.buildings = buildings;
        this.monsterGroup = monsterGroup;
    }

    public static Location createNewDiggersCityLocation(String name,
                                                        int x,
                                                        int y,
                                                        List<BuildingDTO> buildingsDto) {
        var id = UUID.randomUUID().toString();
        var buildings = buildingsDto.stream()
            .map((BuildingDTO dto) -> Building.fromDto(dto, id))
            .collect(Collectors.toList());

        return new Location(id, name, 0, Region.DIGGERS, x, y, buildings,
            MonsterGroup.createEmpty());
    }

    public static Location createNewEmptyDiggersLocation(int x, int y, int level) {
        var id = UUID.randomUUID().toString();
        var name = String.format("location (%s/%s) - $%s region", x, y, Region.DIGGERS);
        var monstersGroup = MonsterGroup.createForLevel(level, Region.DIGGERS);

        return new Location(id, name, level, Region.DIGGERS, x, y, Building.emptyList(),
            monstersGroup);
    }

    public LocationFullDTO toDto() {
        var buildingsDto = buildings.stream()
            .map(Building::toDto)
            .collect(Collectors.toList());

        return new LocationFullDTO(locationName, x, y, buildingsDto);
    }
}
