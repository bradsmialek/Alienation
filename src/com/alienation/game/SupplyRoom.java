package com.alienation.game;

import java.util.HashMap;
import java.util.Map;

/**
 * Supply room
 * Room has a cage that keeps expensive and rare parts locked up.
 * Cage needs a code to open and it has "Ignition switch" that can be useful to fly back home
 * Use the code you got from Alien Room by killing Alien.
 */

public class SupplyRoom {

    /*************** PRIVATE VARIABLE DECLARATIONS  ******************/
    private static final String initialStory = Engine.ANSI_BLUE + "\n\nYou've entered a dim room with racks of common supplies. This must be the supply room.  There's a big metal\n" +
            "cage where all the rare and expensive parts are kept, but it's locked. The only way to open it is at the computer terminal with\n" +
            "an access code. Something is dripping onto your shoulder... some sort of slimey viscous substance. It looks like one computer still\n" +
            "works, but you need an access code.\n" + Engine.ANSI_RESET;
    private static final String updatedStory = Engine.ANSI_BLUE + "\n\nYou're back in the Server room and you have found an ignition switch. This thing must do something, but what!?\n"+ Engine.ANSI_RESET;
    private static final String lastStory = Engine.ANSI_BLUE + "\n\nYou are back in the Server Room. You have the access code to open the cage. You enter the code into the terminal and\n" +
            "you hear metal grinding on metal and then a loud click."+ Engine.ANSI_RESET;
    private static Map<String,Boolean> availableItems = new HashMap<String, Boolean>();
    private static final Map<String,Rooms> availableDirections = new HashMap<String, Rooms>();
    private static final int minusOxy = 10;


    /*************** PUBLIC METHODS  ******************/
    // This method used to load Environment to user
    public static void loadEnvironment(){
        System.out.println(Banner.getBanner());
        Oxygen.minOxygen(minusOxy);
        Oxygen.checkOxy();
        System.out.println(getStory());
        Menu.displayMenu();

    }

    /*************** GETTER - SETTER METHODS  ******************/
    // Get Story line while page loads
    //check for items
    public static String getStory() {
        if (!Character.getInventory().containsKey("Code")) { //if inv does not contain code
            return initialStory;
        } else {
            if(!Character.getInventory().containsKey("Ignition Switch")){ //has code, no ignition
                return lastStory;
            }
            return updatedStory;
        }
    }

    // Get available items of a room
    public static Map<String,Boolean> getAvailableItems(){
        if (availableItems.size() == 0) {
            //availableItems.put("Ignition Switch", true); //TODO: take out after testing... only available if char has code
            //availableItems.put("Code", true);  //TODO: take out after testing... alien will drop this upon death
            availableItems.put("Computer", true);
            availableItems.put("Desk", true);
            availableItems.put("Sofa", true);
            availableItems.put("Racks", true);
            availableItems.put("Supplies", true);
            availableItems.put("Cage", true);
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
