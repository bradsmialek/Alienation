package com.alienation.game;

import java.util.HashMap;
import java.util.Map;

/**
 * User will find out there is one Alien in this room
 * User has to kill Alien to get the code
 */

public class AlienRoom {

    /*************** PRIVATE VARIABLE DECLARATIONS  ******************/
    private static String answer;
    private static final String initialStory = Engine.ANSI_BLUE + "\n\nYou've entered a nasty smelling room. It smells like rotting flesh and the floor is covered in the same slimey substance that\n " +
            "was on your shoulder.  Wait... you see something in the corner slumped over and moving back and forth.\n"+ Engine.ANSI_RESET;
    private static final String updatedStory = Engine.ANSI_BLUE + "\n\nUpdate route\n"+ Engine.ANSI_RESET;
    private static final String lastStory = Engine.ANSI_BLUE + "\n\nYou are back in the room where you killed your crew member.... I mean alien. Nothing has changed except the pools of your friends blood.\n" +
            "OOPS... did it again."+ Engine.ANSI_RESET;
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
        if (!getAvailableItems().containsKey("Alien")) {
            return lastStory;
        } else {
            if(getAvailableItems().get("Alien")){
                return initialStory;
            }
            else{
                return updatedStory;
            }
        }
    }
    //TODO: WORKING ON THIS.  Testing STORYLINE// Might need to use later
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
            availableItems.put("Alien", true);
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
