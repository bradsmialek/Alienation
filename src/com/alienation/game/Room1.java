package com.alienation.game;

/**
 * Created by bradsmialek on Fri - 7/17/20 @ 8:19 AM
 * Build Room 1
 */
public class Room1 {

//    INVESTIGATE, OPEN, EAT, STORE, ATTACK, READ

    private static String actionQuestion = "What would you like to do?";
    private static String answer;



    public static void Environment(){
        System.out.println("As you open your eyes your vision is blurry and your body hurts. You gasp to take your first breath as you wake from cryo-sleep.\n" +
                "You can tell the oxygen levels are low as it seems harder to breathe.  As you look around you notice that there is one crew member missing and\n" +
                "their sleeping capsule is shattered. The ship seems to be drifting in space and the lights are dim, most likely on some sort of backup system.\n" +
                "You notice a taser on the floor.\n\n" +
                actionQuestion + "              " + "Health: 100" + "  " + "Oxygen: 50" + "  " + "Weapon: Tazer" + "\n");
    }
}
