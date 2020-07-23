package com.alienation.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Kitchen - This is the room where user can go and investigate
 * User can eat available edible items and increase health points
 */

public class Kitchen {

    /*************** PRIVATE VARIABLE DECLARATIONS  ******************/
    private static String answer;
    private static final String initialStory = Engine.ANSI_BLUE + "\n\nYou've entered the kitchen and you can immediately tell that something has gotten into the food. There are all\n" +
            "types of nasty smells filling the air, but you are so hungry that the smells can't bother you at this point. There must be something\n" +
            "that you can eat laying around here somewhere?\n"+ Engine.ANSI_RESET;
    private static final String updatedStory = Engine.ANSI_BLUE + "\n\nYou are back in the kitchen where you found that delicious snack. It looks like something's been through here\n +" +
            "here again.\n"+ Engine.ANSI_RESET;
//    private static final String lastStory = Engine.ANSI_BLUE + "\n\nyou"+ Engine.ANSI_RESET;
    private static Map<String,Boolean> availableItems = new HashMap<String, Boolean>();
    private static final Map<String,Rooms> availableDirections = new HashMap<String, Rooms>();
    private static int count = 0;
    private static final int minusOxy = 10;


    /*************** PUBLIC METHODS  ******************/
    // This method used to load Environment to user
    public static void loadEnvironment(){
        count++;
        Oxygen.minOxygen(minusOxy);
        Oxygen.checkOxy();
        System.out.println(getStory());
        Menu.displayMenu();
    }

    /*************** GETTER - SETTER METHODS  ******************/
    // Get Story line while page loads
    public static String getStory() {
        if (!getAvailableItems().containsKey("Snickers")) {
            return initialStory;
        } else {
            return updatedStory;
        }
    }

    // Get available items of a room
    public static Map<String,Boolean> getAvailableItems(){
        if (availableItems.size() == 0) {
            availableItems.put("Refrigerator", true);
            availableItems.put("Microwave", true);
            availableItems.put("Cabinets", true);
            availableItems.put("Dustbin", true);
            availableItems.put("Snickers", true);
        }
        return availableItems;
    }

    // set available items to false by key if item moved to Inventory
    public static void setAvailableItems(Map<String,Boolean> newAvailableItems) {
        availableItems = newAvailableItems;
    }

    // Get available directions from a room
    public static Map<String,Rooms> getAvailableDirections(){
        availableDirections.put("N",null);
        availableDirections.put("S", Rooms.AlienRoom);
        availableDirections.put("E",null);
        availableDirections.put("W", Rooms.ComputerRoom);
        return availableDirections;
    }
}
