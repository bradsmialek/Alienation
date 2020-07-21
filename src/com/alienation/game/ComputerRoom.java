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
    private static final String initialStory = Engine.ANSI_BLUE + "\n\nYou've entered a dim room, except the blinking of tiny lights everywhere. The annoying beeping noises of computers fill the\n" +
            "air and it's hard to concentrate. This must be where the system's operating servers and backup drives are accessed. Something is\n" +
            "dripping onto your shoulder... some sort of slimey viscous substance. It looks like one computer still works, but you need an\n" +
            "access code.\n" + Engine.ANSI_RESET;
    private static final String updatedStory = Engine.ANSI_BLUE + "\n\nYou're back in the Server room and you have found an ignition switch. This thing must do something, but what!?\n"+ Engine.ANSI_RESET;
    private static final String lastStory = Engine.ANSI_BLUE + "\n\nYou are back in the Server Room. You have found an access code... where can I use it?"+ Engine.ANSI_RESET;
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
    //TODO:  Check for access code also and do something
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
            availableItems.put("Ignition Switch", true); //TODO: take out after testing... only available if char has code.. how.. don't know yet?
            availableItems.put("Computer", true);
            availableItems.put("Desk", true);
            availableItems.put("Sofa", true);
            availableItems.put("Bookshelf", true);
            availableItems.put("Lamp", true);
            availableItems.put("Code", true);  //TODO: take out after testing... alien will drop this upon death
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
