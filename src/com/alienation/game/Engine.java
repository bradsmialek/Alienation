package com.alienation.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Game Engine
 */
public class Engine {

    private static String actionQuestion = "What would you like to do?";
    private static String answer;
    public static Map<String, Boolean> initialItems = getInitialItems();

    public static Map<String, Boolean> getInitialItems() {
        Map<String,Boolean> availableItems = new HashMap<String, Boolean>();
        availableItems.put("tazer",true);
        availableItems.put("alien1",true);
        availableItems.put("snickers",true);
        availableItems.put("ignitionswitch",true);
        return availableItems;
    }

    public static void setInitialItems(Map<String, Boolean> initialItems) {
        Engine.initialItems = initialItems;
    }

    public static void Start(){
        Scanner in = new Scanner(System.in);
        System.out.println("\n" +
                "░█████╗░██╗░░░░░██╗███████╗███╗░░██╗░█████╗░████████╗██╗░█████╗░███╗░░██╗\n" +
                "██╔══██╗██║░░░░░██║██╔════╝████╗░██║██╔══██╗╚══██╔══╝██║██╔══██╗████╗░██║\n" +
                "███████║██║░░░░░██║█████╗░░██╔██╗██║███████║░░░██║░░░██║██║░░██║██╔██╗██║\n" +
                "██╔══██║██║░░░░░██║██╔══╝░░██║╚████║██╔══██║░░░██║░░░██║██║░░██║██║╚████║\n" +
                "██║░░██║███████╗██║███████╗██║░╚███║██║░░██║░░░██║░░░██║╚█████╔╝██║░╚███║\n" +
                "╚═╝░░╚═╝╚══════╝╚═╝╚══════╝╚═╝░░╚══╝╚═╝░░╚═╝░░░╚═╝░░░╚═╝░╚════╝░╚═╝░░╚══╝\n\n");

        System.out.println("As you open your eyes your vision is blurry and your body hurts. You gasp to take your first breath as you wake from cryo-sleep.\n" +
                "You can tell the oxygen levels are low as it seems harder to breathe.  As you look around you notice that there is one crew member missing and\n" +
                "their sleeping capsule is shattered. The ship seems to be drifting in space and the lights are dim, most likely on some sort of backup system.\n" +
                "You notice a taser on the floor.\n\n" +
                actionQuestion + "              " + "Health: 100" + "  " + "Oxygen: 50" + "  " + "Weapon: " + "\n");

        System.out.println("You can: Investigate, Open, Eat, Pick up, Attack, Read, Swap");
        System.out.println("You can move: N, S, E, W");
        actionQuestion = in.nextLine();
        System.out.println(actionQuestion);





        in.close(); // CLOSES SCANNER
    }


}
