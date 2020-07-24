package com.alienation.game;

/**
 * Edibles Enum
 */
public enum Edibles{
    SNICKERS("Snickers",20),
    CHIPS("Chips",20);

    private String name;
    private int healthPoints;

    Edibles(String name,int healthPoints) {
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
