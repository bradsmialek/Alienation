package com.alienation.game;

import java.util.Arrays;

/**
 * Weapons Enum
 */
public enum Weapons{
    TASER_GUN("Taser Gun", 1),
    LASER("Laser", 3),
    SQUIRT_GUN("Squirt Gun", 4),
    FLAMETHROWER("Flamethrower", 8);

    private String name;
    private int damagePoints;

    Weapons(String name, int damagePoints) {
        this.damagePoints = damagePoints;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public int getDamagePoints() {
        return damagePoints;
    }

    public static Weapons findWeaponsByName(String name) throws Exception {
        return Arrays.stream(Weapons.values()).filter(v ->
                v.getName().equals(name)).findFirst().orElseThrow(() ->
                new Exception(String.format("Unknown Weapons.name: '%s'", name)));
    }
}
