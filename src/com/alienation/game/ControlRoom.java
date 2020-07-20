package com.alienation.game;

import java.util.HashMap;
import java.util.Map;

/**
 * Capsule Room - This is the room where the character wakes up
 * User will see this room first when start the game
 */
public class ControlRoom {

    /*************** PRIVATE VARIABLE DECLARATIONS  ******************/
    private static String answer;
    private static String initialStory = "\n\nThis is Control Room.\n";
    private static String updatedStory = "\n\nThis is Control Room - Updated.\n";
    private static String lastStory = "\n\nYou are back in the Capsule Room. Nothing has changed.";
    private static Map<String,Boolean> availableItems = new HashMap<String, Boolean>();
    private static Map<String,Rooms> availableDirections = new HashMap<String, Rooms>();
    private static int count = 0;
    private static int minusOxy = 10;


    /*************** PUBLIC METHODS  ******************/
    // This method used to load Environment to user
    public static void loadEnvironment(){
        count++;
        Oxygen.setOxygen(minusOxy);
        System.out.println(getStory());
        Menu.displayMenu();
    }


    /*************** GETTER - SETTER METHODS  ******************/
    // Get Story line while page loads
    public static String getStory() {
        return initialStory;
    }
    //    public static String getStory() {
//        if(count == 1){
//            return initialStory;
//        }
//        else if(count == 2){
//            return updatedStory;
//        }
//        else{
//            return lastStory;
//        }
//    }

    // Get available items of a room
    public static Map<String,Boolean> getAvailableItems(){
        if (availableItems.size() == 0) {
            availableItems.put("Monitor", true);
            availableItems.put("Control Panel", true);
            availableItems.put("Pilot Seats", true);
        }
        return availableItems;
    }

    // set available items to false by key if item moved to Inventory
    public static void setAvailableItems(Map<String,Boolean> newAvailableItems) {
        availableItems = newAvailableItems;
    }

    // Get available directions from a room
    public static Map<String,Rooms> getAvailableDirections(){
        availableDirections.put("N", Rooms.CapsuleRoom);
        availableDirections.put("S",null);
        availableDirections.put("E",null);
        availableDirections.put("W",null);
        return availableDirections;
    }
}
