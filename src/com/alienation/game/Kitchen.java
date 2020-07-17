package com.alienation.game;

import java.util.HashMap;
import java.util.Map;

/**
 * Kitchen - This is the room where user can go and investigate
 * User can eat available edible items and increase health points
 */

public class Kitchen {

    /*************** PRIVATE VARIABLE DECLARATIONS  ******************/
    private static String answer;
    private static String initialStory = "As you open your eyes your vision is blurry and your body hurts. You gasp to take your first breath as you wake from cryo-sleep.\n" +
            "You can tell the oxygen levels are low as it seems harder to breathe.  As you look around you notice that there is one crew member missing and\n" +
            "their sleeping capsule is shattered. The ship seems to be drifting in space and the lights are dim, most likely on some sort of backup system.\n" +
            "You notice a Tazer on the floor.\n";
    private static String updatedStory = "As you open your eyes your vision is blurry and your body hurts. You gasp to take your first breath as you wake from cryo-sleep.\n" +
            "You can tell the oxygen levels are low as it seems harder to breathe.  As you look around you notice that there is one crew member missing and\n" +
            "their sleeping capsule is shattered. The ship seems to be drifting in space and the lights are dim, most likely on some sort of backup system.\n" +
            "You notice a Tazer on the floor.\n";
    private static Map<String,Boolean> availableItems = new HashMap<String, Boolean>();
    private static Map<String,Rooms> availableDirections = new HashMap<String, Rooms>();


    /*************** PUBLIC METHODS  ******************/
    // This method used to load Environment to user
    public static void loadEnvironment(){
        System.out.println("Kitchen");
        System.out.println(getStory());
        Menu.displayMenu();
    }

    /*************** GETTER - SETTER METHODS  ******************/
    // Get Story line while page loads
    public static String getStory() {
        if (!getAvailableItems().containsKey("snickers")) {
            return initialStory;
        } else {
            if(getAvailableItems().get("snickers")){
                return initialStory;
            }
            else{
                return updatedStory;
            }
        }
    }

    // Get available items of a room
    public static Map<String,Boolean> getAvailableItems(){
        availableItems.put("snickers",true);
        return availableItems;
    }

    // set available items to false by key if item moved to Inventory
    public static void setAvailableItems(String key) {
        availableItems.replace(key,false);
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
