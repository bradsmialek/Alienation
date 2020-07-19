package com.alienation.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Capsule Room - This is the room where the character wakes up
 * User will see this room first when start the game
 */
public class CapsuleRoom {

    /*************** PRIVATE VARIABLE DECLARATIONS  ******************/
    private static String answer;
    private static String initialStory = "As you open your eyes your vision is blurry and your body hurts. You gasp to take your first breath as you wake from cryo-sleep.\n" +
            "You can tell the oxygen levels are low as it seems harder to breathe.  As you look around you notice that there is one crew member missing and\n" +
            "their sleeping capsule is shattered. The ship seems to be drifting in space and the lights are dim, most likely on some sort of backup system.\n" +
<<<<<<< HEAD
            "You notice a Tazer on the floor.\n\n";
    private static String updatedStory = "Updated\n\n";
    private static Map<String,Boolean> availableItems = new HashMap<String, Boolean>();
    private static Map<String,String> availableDirections = new HashMap<String, String>();
    private static int count = 0;
=======
            "You notice a Tazer on the floor.\n";
    private static String updatedStory = "This is Capsule Room - Updated\n";
    private static Map<String,Boolean> availableItems = new HashMap<String, Boolean>();
    private static Map<String,Rooms> availableDirections = new HashMap<String, Rooms>();
    private static int count = 0;

>>>>>>> 9a21e2f43bed4251c360886f6907a1c6edd2a434

    /*************** PUBLIC METHODS  ******************/
    // This method used to load Environment to user
    public static void loadEnvironment(){
        count++;
<<<<<<< HEAD
        System.out.println(getInitialStory());
        Menu.ShowMenu();
=======
        System.out.println(getStory());
        Menu.displayMenu();
>>>>>>> 9a21e2f43bed4251c360886f6907a1c6edd2a434
    }


    /*************** GETTER - SETTER METHODS  ******************/
<<<<<<< HEAD
    public static String getInitialStory() {
=======
    // Get Story line while page loads
    public static String getStory() {
>>>>>>> 9a21e2f43bed4251c360886f6907a1c6edd2a434
        if(count > 2){
            return updatedStory;
        }
        else{
            return initialStory;
        }
//        if (!getAvailableItems().containsKey("tazer")) {
//            return initialStory;
//        } else {
//            if(getAvailableItems().get("tazer")){
//                return initialStory;
//            }
//            else{
//                return updatedStory;
//            }
//        }
    }

<<<<<<< HEAD
//    public static String getStory() {
//        if (!getAvailableItems().containsKey("tazer")) {
//            return initialStory;
//        } else {
//            return updatedStory;
//        }
//    }

    public static Map<String,Boolean> getAvailableItems(){
        availableItems.put("tazer",true);

//        Set<String> keys = availableItems.keySet();
//        System.out.println("You see:");
//        for (String key : keys) {
//            System.out.println(key);
//        }
//        System.out.println("\n");
//        Menu.ShowMenu();
=======
    // Get available items of a room
    public static Map<String,Boolean> getAvailableItems(){
        if (availableItems.size() == 0) {
            availableItems.put("Cabinet", true);
            availableItems.put("Tazer", true);
            availableItems.put("Chips", true);
        }
>>>>>>> 9a21e2f43bed4251c360886f6907a1c6edd2a434
        return availableItems;
    }

    // set available items updated items if item moved to Inventory or Eat an item
    public static void setAvailableItems(Map<String,Boolean> newAvailableItems) {
        availableItems = newAvailableItems;
    }

    // remove available items by key if character eats the item
    public static void removeAvailableItems(String key) {
        availableItems.remove(key);
    }

    // Get available directions from a room
    public static Map<String,Rooms> getAvailableDirections(){
        availableDirections.put("N",Rooms.ComputerRoom);
        availableDirections.put("S",Rooms.ControlRoom);
        availableDirections.put("E",Rooms.AlienRoom);
        availableDirections.put("W",null);
        return availableDirections;
    }

<<<<<<< HEAD
    public static void grab(){
        System.out.println("Grab what?");
        Set<String> keys = availableItems.keySet();
        for (String key : keys) {
            System.out.println(key);
        }
        System.out.println("\n");

    }
}
=======
//    // Grab item from room
//    public static void investigate(){
//        System.out.println("You see:");
//        for (String key : getAvailableItems()) {
//            System.out.println(key);
//        }
//        System.out.println("\n");
//        Menu.displayMenu();
//    }
//
//    // Grab item from room
//    public static void grab(){
//        System.out.println("Grab what?");
//        Set<String> keys = getAvailableItems();
//        for (String key : keys) {
//            System.out.println(key);
//        }
//        System.out.println("\n");
//    }
}
>>>>>>> 9a21e2f43bed4251c360886f6907a1c6edd2a434
