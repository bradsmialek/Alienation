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
    private static final String initialStory = Engine.ANSI_BLUE + "\n\nYou've entered a large room with navigation stations and controls to pilot the ship. The view of the vast dark space around you\n " +
            "is mesmerizing! You're immediate thought is to send a message for help, but all contact between Earth and you has been disabled.\n " +
            "Maybe you can pilot the ship back home?  You climb into the pilot ship and you notice the starter panel looks fried. You know\n " +
            "enough to know that you have to find an ignition switch and pray that works.\n"+ Engine.ANSI_RESET;
    private static final String updatedStory = Engine.ANSI_BLUE + "\n\nYou are back in the Control Room. Nothing has changed. You need an ignition switch!"+ Engine.ANSI_RESET;
    private static final String lastStory = Engine.ANSI_BLUE + "\n\nYou're back in the Control room and you have finally found an ignition switch! You replace the old one and hit the switch, and what\n " +
            "you hear next is the sweetest sound you've ever heard!  The engines fire up and you enter Earth's coordinates on auto pilot.\n " +
            "You're finally headed home, the aliens are dead, and the rest of the crew begin to wake up. What will you tell them?\n"+ Engine.ANSI_RESET;
    //TODO : add timeout END GAME and ps.  You feel movement in your belly...
    private static Map<String,Boolean> availableItems = new HashMap<String, Boolean>();
    private static final Map<String,Rooms> availableDirections = new HashMap<String, Rooms>();
    private static final Map<String,String > charInventory = new HashMap<String, String>();
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
    // check inventory for part
    public static String getStory() {
        if (!Character.getInventory().containsKey("Ignition Switch")) { //if inventory does not contain ignition
            System.out.println("no switch");
            return initialStory;
        } else{
            return lastStory;
            //TODO:  end game scene on timeout... then exit system... or go to start game scene??
        }
    }

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
