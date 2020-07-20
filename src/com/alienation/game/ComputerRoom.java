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
    private static String initialStory = "\n\nYou've entered a dim room, except the blinking of tiny lights everywhere. The annoying beeping noises of computers fill the\n" +
            "air and it's hard to concentrate. This must be where the system's operating servers and backup drives are accessed. Something is\n" +
            "dripping onto your shoulder... some sort of slimey viscous substance. It looks like one computer still works, but you need an\n" +
            "access code.\n";
    private static String updatedStory = "\n\nYou're back in the server room and you have found an ignition switch.  This thing must do something, but what!?\n";
    private static String lastStory = "\n\nYou are back in the Capsule Room. Nothing has changed.";
    private static Map<String,Boolean> availableItems = new HashMap<String, Boolean>();
    private static Map<String,Rooms> availableDirections = new HashMap<String, Rooms>();
    private static int count = 0;
    private static int minusOxy = 10;


    /*************** PUBLIC METHODS  ******************/
    // This method used to load Environment to user
    public static void loadEnvironment(){
        count++;
        Oxygen.minOxygen(minusOxy);
        if(Oxygen.getOxygen() == 0){
            System.out.println("You're Dead! There is no more Oxygen left."); // Better Death
            Death.death();
            System.exit(0);
        }
        System.out.println(getStory());
        Menu.displayMenu();
    }

    /*************** GETTER - SETTER METHODS  ******************/
    // Get Story line while page loads
    public static String getStory() {
        if (!getAvailableItems().containsKey("Ignition Switch")) { //if room does not contain ignition
            return updatedStory;
        } else {
            if(getAvailableItems().get("Ignition Switch")){  // true
//                System.out.println(getAvailableItems().get("Ignition Switch"));
                return initialStory;
            }
            else{
                return "blah";
            }
        }
    }
    //TODO: WORKING ON THIS.  NEED TO FIGURE OUT STORYLINE
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
