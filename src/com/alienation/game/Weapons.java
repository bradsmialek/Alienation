package com.alienation.game;

/**
 * Weapons Enum
 */
public enum Weapons{
    TASER_GUN(1),
    LASER(3),
    SQUIRT_GUN(4),
    FLAMETHROWER(8);

    private final int damagePoints;

    Weapons(final int damagePoints) {
        this.damagePoints = damagePoints;
    }

    public int getDamagePoints() {
        return damagePoints;
    }
}
