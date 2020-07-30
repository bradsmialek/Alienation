/*
 *      Alienation
 *      TLG Learning: Capstone Project
 *      Originally Created by Team Alienation &&
 *      Edited by Team Capstone Mafia
 *      https://github.com/Capstone-Mafia
 *
 *      Team Alienation Members:
 *      Brad Smialek (https://github.com/bradsmialek)
 *      Dhruti Kosta (https://github.com/dhruti-kosta)
 *      Terrell Douglas (https://github.com/Dougie105)
 *      Original project repo:
 *      https://github.com/bradsmialek/Alienation
 *
 *      Capstone Mafia Members:
 *      Bruce West (https://github.com/BruceBAWest)
 *      Gurinder Batth (https://github.com/GurinderB)
 *      Daeun Lok (https://github.com/koreareefclub)
 *      Capstone Mafia fork:
 *      https://github.com/Capstone-Mafia/Alienation
 */

package com.alienation.coregamefiles.enums;

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
