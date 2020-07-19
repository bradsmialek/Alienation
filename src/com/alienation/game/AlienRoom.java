package com.alienation.game;

import java.util.HashMap;
import java.util.Map;

/**
 * Alien Room - This is the room where user can go and investigate
 * User will find out there is one Alien in this room
 * User has to kill Alien to get the code
 */

public class AlienRoom {

    /*************** PRIVATE VARIABLE DECLARATIONS  ******************/
    private static String answer;
    private static String initialStory = "This is Alien Room.\n";
    private static String updatedStory = "This is Alien Room - Updated.\n";
    private static Map<String,Boolean> availableItems = new HashMap<String, Boolean>();
    private static Map<String,Rooms> availableDirections = new HashMap<String, Rooms>();


    /*************** PUBLIC METHODS  ******************/
    // This method used to load Environment to user
    public static void loadEnvironment(){
        System.out.println(getStory());
        Menu.displayMenu();
    }


    /*************** GETTER - SETTER METHODS  ******************/
    // Get Story line while page loads
    public static String getStory() {
        if (!getAvailableItems().containsKey("Alien - Type 1")) {
            return initialStory;
        } else {
            if(getAvailableItems().get("Alien - Type 1")){
                return initialStory;
            }
            else{
                return updatedStory;
            }
        }
    }

    // Get available items of a room
    public static Map<String,Boolean> getAvailableItems(){
        if (availableItems.size() == 0) {
            availableItems.put("Alien - Type 1", true);
            availableItems.put("Bed", true);
            availableItems.put("Mirror", true);
            availableItems.put("Old Box", true);
        }
        return availableItems;
    }

    // set available items to false by key if item moved to Inventory
    public static void setAvailableItems(Map<String,Boolean> newAvailableItems) {
        availableItems = newAvailableItems;
    }

    // Get available directions from a room
    public static Map<String,Rooms> getAvailableDirections(){
        availableDirections.put("N",Rooms.Kitchen);
        availableDirections.put("S",null);
        availableDirections.put("E",null);
        availableDirections.put("W",Rooms.CapsuleRoom);
        return availableDirections;
    }
}
