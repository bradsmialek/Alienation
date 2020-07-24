package com.alienation.game;

import java.util.HashMap;
import java.util.Map;

/**
 * User will find out there is one Alien in this room
 * User has to kill Alien to get the code
 */

public class AlienRoom {

    /*************** PRIVATE VARIABLE DECLARATIONS  ******************/
    private static final String initialStory = Engine.ANSI_BLUE + "\n\nYou've entered a nasty smelling room. It smells like rotting flesh and the floor is covered with blood and a slimey substance.\n" +
            "Wait... you see something in the corner slumped over, moving back and forth.\n"+ Engine.ANSI_RESET;
    private static final String updatedStory = Engine.ANSI_BLUE + "\n\nLast time you were here you fought the alien. It's still in here.... I can smell it.\n"+ Engine.ANSI_RESET;
    private static final String lastStory = Engine.ANSI_BLUE + "\n\nYou are back in the room where you killed your crew member.... I mean alien. Nothing has changed except the pools of your friends blood.\n" +
            "OOPS... did it again."+ Engine.ANSI_RESET;
    private static Map<String,Boolean> availableItems = new HashMap<String, Boolean>();
    private static final Map<String,Rooms> availableDirections = new HashMap<String, Rooms>();
    private static final int minusOxy = 10;
    private static int count = 0;

    /*************** PUBLIC METHODS  ******************/
    // This method used to load Environment to user
    public static void loadEnvironment(){
        count++;
        System.out.println(Banner.getBanner());
        Oxygen.minOxygen(minusOxy);
        Oxygen.checkOxy();
        System.out.println(getStory());
        Menu.displayMenu();
    }

    /*************** GETTER - SETTER METHODS  ******************/
    // Get Story line while page loads
    public static String getStory() {
        if (!getAvailableItems().containsKey("Humanoid")) {
            return lastStory;
        }else{
            if(count >1 && getAvailableItems().get("Humanoid")){
                return updatedStory;
            }else if(getAvailableItems().get("Humanoid")){
                return initialStory;
            }
        }

        return null;
    }

    // Get available items of a room
    public static Map<String,Boolean> getAvailableItems(){
        if (availableItems.size() == 0) {
            availableItems.put("Humanoid", true);
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
