package com.alienation.game;

/**
 * Oxygen Class
 */
public class Oxygen {
    public static int oxygen = 40;
    //TODO: MAKE MAX OXYGEN
    public static int getOxygen() {
        return oxygen;
    }

    public static void minOxygen(int minusOxy) {
        Oxygen.oxygen = Oxygen.oxygen - minusOxy;
    }

    public static void incOxygen(int incOxy) {
        Oxygen.oxygen = Oxygen.oxygen + incOxy;
        //TODO: IF OVER 100 SET IT TO MAX
    }
}
