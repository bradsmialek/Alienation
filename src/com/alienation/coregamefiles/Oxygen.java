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

package com.alienation.coregamefiles;

import com.alienation.coregamefiles.gameart.Death;

import static com.alienation.coregamefiles.gameart.TextColors.*;
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

    public static void setOxygen(int newOxygen) {
        Oxygen.oxygen = newOxygen;
    }

    //Decreases oxygen levels
    public static void minOxygen(int minusOxy) {
        if(Oxygen.oxygen - minusOxy < 0){
            Oxygen.oxygen = 0;
        } else {
            Oxygen.oxygen = Oxygen.oxygen - minusOxy;
        }
        System.out.println(ANSI_RED + "-10 " + oTwo + ANSI_RESET);
    }

    //Increases oxygen levels SETTERS
    public static void incOxygen(int incOxy) {
        Oxygen.oxygen = Oxygen.oxygen + incOxy;
        //TODO: IF OVER 100 SET IT TO MAX
    }

    // checks oxygen levels
    public static void checkOxy(){
        if(Oxygen.getOxygen() == 0){
            System.out.println(ANSI_RED + "\n\nOxygen depleted..." + ANSI_RESET); // Better Death
            Death.death();
        }
    }
}