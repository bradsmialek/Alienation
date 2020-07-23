package com.alienation.game;

/**
 * Weapons Enum
 */
public enum Weapons{
    TASER("Taser", 1),
    LASER("Laser", 3),
    SQUIRTGUN("Squirt Gun", 4),
    FLAMETHROWER("Flamethrower", 8);

    private final String name;
    private final int damagePoints;

    Weapons(final String name, final int damagePoints) {
        this.damagePoints = damagePoints;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getDamagePoints() {
        return damagePoints;
    }
}
