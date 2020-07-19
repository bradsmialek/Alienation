package com.alienation.game;

/**
 * Oxygen Class
 */
public class Oxygen {
    public static int oxygen = 40;

    public static int getOxygen() {
        return oxygen;
    }

    public static void setOxygen(int minusOxy) {
        Oxygen.oxygen = Oxygen.oxygen - minusOxy;
    }
}
