package com.alienation.game;

import java.util.HashMap;
import java.util.Map;

/**
 * Alien Class
 */
public class Alien {

    private static int t1Hp = 4; // type 1 health points
    private static int t2Hp = 6;
    private static int t3Hp = 10;
    private static int t4Hp = 50;
    private static final int t1Dmg = 1; // type 1 damage dealt
    private static final int t2Dmg = 3;
    private static final int t3Dmg = 5;
    private static final int t4Dmg = 10;
    private static Map<String,Boolean> alienTypes = new HashMap<String, Boolean>();

    // Get the alien
    public static Map<String,Boolean> getAliens(){
        if(alienTypes.size() == 0) {
            alienTypes.put("Vermin", true);
            alienTypes.put("Canine", true);
            alienTypes.put("Humanoid", true);
            alienTypes.put("Superhumanoid", true); // Ultimate
        }
        return alienTypes;
    }

    // Getters and Setters for health and damage
    public static int getT1Hp() {
        return t1Hp;
    }

    public static void setT1Hp(int t1Hp) {
        Alien.t1Hp = t1Hp;
    }

    public static int getT2Hp() {
        return t2Hp;
    }

    public static void setT2Hp(int t2Hp) {
        Alien.t2Hp = t2Hp;
    }

    public static int getT3Hp() {
        return t3Hp;
    }

    public static void setT3Hp(int t3Hp) {
        Alien.t3Hp = t3Hp;
    }

    public static int getT4Hp() {
        return t4Hp;
    }

    public static void setT4Hp(int t4Hp) {
        Alien.t4Hp = t4Hp;
    }

    public static int getT1Dmg() {
        return t1Dmg;
    }

    public static int getT2Dmg() {
        return t2Dmg;
    }

    public static int getT3Dmg() {
        return t3Dmg;
    }

    public static int getT4Dmg() {
        return t4Dmg;
    }

}

