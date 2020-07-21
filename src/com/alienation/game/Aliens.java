package com.alienation.game;

/**
 * Created by bradsmialek on Mon - 7/20/20 @ 6:47 PM
 * Alien Enums - 4 types
 */
public enum Aliens {
    RAT("T1",1),
    DOG("T2",3),
//    KIND?("T3",5), // Some kind of alien...
    ALIEN("T4", 10); // IS ULTIMATE

    private final String name;
    private final int dealDamage;

    Aliens(final String name,final int damage) {
        this.name = name;
        this.dealDamage = damage;
    }

    public String getName() {
        return name;
    }
    public int getHealthPoints() {
        return dealDamage;
    }
}
