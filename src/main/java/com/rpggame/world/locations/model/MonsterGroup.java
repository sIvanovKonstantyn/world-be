package com.rpggame.world.locations.model;

/*
 * MonsterGroup:
 * A point to start the battle with monsters. Has a list of potential monsters to fight
 * Provides a random amount of monsters for specific battle
 * TODO: add monsters
 * */
public class MonsterGroup {
    private String monsterGroupName;

    private MonsterGroup(String monsterGroupName) {
        this.monsterGroupName = monsterGroupName;
    }

    public static MonsterGroup createEmpty() {
        return new MonsterGroup("empty");
    }

    public static MonsterGroup createForLevel(int level, Region region) {
        return new MonsterGroup(
            String.format("Monsters with level: %s from region: %s", level, region));
        //TODO: add monsters
    }
}
