package com.alienation.game;

import java.util.Scanner;

/**
 * Oxygen Class
 */
public class Oxygen {
    public static int oxygen = 50;
    private static final String oTwo = "O\u2082"; // O₂

    //TODO: MAKE MAX OXYGEN
    public static int getOxygen() {
        return oxygen;
    }

    //Decreases oxygen levels  SETTERS
    public static void minOxygen(int minusOxy) {
        Oxygen.oxygen = Oxygen.oxygen - minusOxy;
        //TODO: wanna discuss with @Brad
        System.out.println(Engine.ANSI_RED + "-10 " + oTwo + Engine.ANSI_RESET);
    }

    //Increases oxygen levels SETTERS
    public static void incOxygen(int incOxy) {
        Oxygen.oxygen = Oxygen.oxygen + incOxy;
        //TODO: IF OVER 100 SET IT TO MAX
    }

    // checks oxygen levels
    public static void checkOxy(){
        if(Oxygen.getOxygen() == 0){
            System.out.println(Engine.ANSI_RED + "\n\nOxygen depleted..." + Engine.ANSI_RESET); // Better Death
            System.out.println(Death.death());
            System.exit(0);
        }
    }
}
