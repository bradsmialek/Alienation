package com.alienation.game;

/**
 * Edibles Enum
 */
public enum Edibles{
    SNICKERS("Snickers",2),
    CHIPS("Chips",3);

    private final String name;
    private final int healthPoints;

    Edibles(final String name,final int healthPoints) {
        this.name = name;
        this.healthPoints = healthPoints;
    }

    public String getName() {
        return name;
    }
    public int getHealthPoints() {
        return healthPoints;
    }
}
