package com.alienation.game;

import java.util.HashMap;
import java.util.Map;

/**
 * ComputerRoom - This is the room where user can go and investigate
 * Room has desk and computer available.
 * Desk drawer need code to open and it has "Ignition switch" that can be useful to fly back home
 * Use the code you got from Alien Room by killing Alien.
 */

public class ComputerRoom {

    /*************** PRIVATE VARIABLE DECLARATIONS  ******************/
    private static String answer;
    private static String initialStory = "This is Computer Room.\n";
    private static String updatedStory = "This is Computer Room - Updated.\n";
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
        if (!getAvailableItems().containsKey("Ignition Switch")) {
            return initialStory;
        } else {
            if(getAvailableItems().get("Ignition Switch")){
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
            availableItems.put("Ignition Switch", true);
            availableItems.put("Computer", true);
            availableItems.put("Desk", true);
            availableItems.put("Sofa", true);
            availableItems.put("Bookshelf", true);
            availableItems.put("Lamp", true);
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
        availableDirections.put("S",Rooms.CapsuleRoom);
        availableDirections.put("E",Rooms.Kitchen);
        availableDirections.put("W",null);
        return availableDirections;
    }
}
