package com.alienation.game;

import java.util.HashMap;
import java.util.Map;

/**
 * Kitchen - This is the room where user can go and investigate
 * User can eat available edible items and increase health points
 */

public class Kitchen {

    /*************** PRIVATE VARIABLE DECLARATIONS  ******************/
    private static String answer;
    private static String initialStory = "As you open your eyes your vision is blurry and your body hurts. You gasp to take your first breath as you wake from cryo-sleep.\n" +
            "You can tell the oxygen levels are low as it seems harder to breathe.  As you look around you notice that there is one crew member missing and\n" +
            "their sleeping capsule is shattered. The ship seems to be drifting in space and the lights are dim, most likely on some sort of backup system.\n" +
            "You notice a Tazer on the floor.\n\n" + Menu.getActionQuestion() + "              " + "Health: 100" + "  " + "Oxygen: 50" + "  " + "Weapon: " + "Tazer" + "\n";
    private static String updatedStory = "As you open your eyes your vision is blurry and your body hurts. You gasp to take your first breath as you wake from cryo-sleep.\n" +
            "You can tell the oxygen levels are low as it seems harder to breathe.  As you look around you notice that there is one crew member missing and\n" +
            "their sleeping capsule is shattered. The ship seems to be drifting in space and the lights are dim, most likely on some sort of backup system.\n" +
            "You notice a Tazer on the floor.\n\n" + Menu.getActionQuestion() + "              " + "Health: 100" + "  " + "Oxygen: 50" + "  " + "Weapon: " + "Tazer" + "\n";
    private static Map<String,Boolean> availableItems = new HashMap<String, Boolean>();
    private static Map<String,String> availableDirections = new HashMap<String, String>();


    /*************** PUBLIC METHODS  ******************/
    public static void loadEnvironment(){
        System.out.println(getInitialStory());
        System.out.println(Menu.getActions());
        System.out.println(Menu.getDirections());
    }

    /*************** GETTER - SETTER METHODS  ******************/
    public static String getInitialStory() {
        if(getAvailableItems().get("snickers") != null){
            if(getAvailableItems().get("snickers")){
                return initialStory;
            }
            else{
                return updatedStory;
            }
        }
        else{
            return initialStory;
        }
    }

    public static Map<String,Boolean> getAvailableItems(){
        availableItems.put("snickers",true);
        return availableItems;
    }

    public static Map<String,String> getAvailableDirections(){
        availableDirections.put("N","");
        availableDirections.put("S","AlienRoom");
        availableDirections.put("E","");
        availableDirections.put("W","ComputerRoom");
        return availableDirections;
    }
}
