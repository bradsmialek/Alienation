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
    private static String initialStory = Engine.ANSI_BLUE + "\n\nAs you open your eyes your vision is blurry and your body hurts. You gasp to take your first breath as you wake from cryo-sleep.\n" +
            "You can tell the oxygen levels are low as it seems harder to breathe.  As you look around you notice that there is one crew member\n" +
            "missing and their sleeping capsule is shattered with blood splattered across the front. The ship seems to be drifting in\n" +
            "space and the lights are dim, most likely on some sort of backup system. You notice a Taser on the floor.\n"+ Engine.ANSI_RESET;
    private static String updatedStory = Engine.ANSI_BLUE + "\n\nYou are back in the Capsule Room. Another crew member is missing. There is a blood trail....\n"+ Engine.ANSI_RESET;
    private static String lastStory = Engine.ANSI_BLUE + "\n\nYou are back in the Capsule Room. Nothing has changed. There are crew members missing and blood everywhere."+ Engine.ANSI_RESET;
    private static Map<String,Boolean> availableItems = new HashMap<String, Boolean>();
    private static Map<String,Rooms> availableDirections = new HashMap<String, Rooms>();
    private static int count = 0;
    private static int minusOxy = 10; // TODO: Make random number??


    /*************** PUBLIC METHODS  ******************/
    // This method used to load Environment to user
    public static void loadEnvironment() throws Exception {
        count++;
        Character.checkHealth();
        Oxygen.minOxygen(minusOxy);
        Oxygen.checkOxy();
        System.out.println(getStory());
        System.out.println(RoomsMap.capsuleRoom());
        Menu.displayMenu();
    }

    /*************** GETTER - SETTER METHODS  ******************/
    // Get Story line while page loads
    public static String getStory() {
        if(count == 1){
            return initialStory;
        }
        else if(count == 2){
            return updatedStory;
        }
        else{
            return lastStory;
        }
    }

    // Get available items of a room
    public static Map<String,Boolean> getAvailableItems(){
        if (availableItems.size() == 0) {
            availableItems.put("Pods", true);
            availableItems.put("Oxygen Tank", true);
            availableItems.put("Racks", true);
            availableItems.put("Lockers", true);
        }
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
}
