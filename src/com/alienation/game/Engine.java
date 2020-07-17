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

        CapsuleRoom.loadEnvironment();
        actionQuestion = in.nextLine();
        System.out.println(actionQuestion);
    }
}
