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
    private static String initialStory = "As you open your eyes your vision is blurry and your body hurts. You gasp to take your first breath as you wake from cryo-sleep.\n" +
            "You can tell the oxygen levels are low as it seems harder to breathe.  As you look around you notice that there is one crew member missing and\n" +
            "their sleeping capsule is shattered. The ship seems to be drifting in space and the lights are dim, most likely on some sort of backup system.\n" +
            "You notice a Tazer on the floor.\n\n";
    private static String updatedStory = "As you open your eyes your vision is blurry and your body hurts. You gasp to take your first breath as you wake from cryo-sleep.\n" +
            "You can tell the oxygen levels are low as it seems harder to breathe.  As you look around you notice that there is one crew member missing and\n" +
            "their sleeping capsule is shattered. The ship seems to be drifting in space and the lights are dim, most likely on some sort of backup system.\n" +
            "You notice a Tazer on the floor.\n\n";
    private static Map<String,Boolean> availableItems = new HashMap<String, Boolean>();
    private static Map<String,String> availableDirections = new HashMap<String, String>();


    /*************** PUBLIC METHODS  ******************/
    public static void loadEnvironment(){
        System.out.println(getInitialStory());

    }

    /*************** GETTER - SETTER METHODS  ******************/
    public static String getInitialStory() {
        if(getAvailableItems().get("ignitionswitch") != null){
            if(getAvailableItems().get("ignitionswitch")){
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
        availableItems.put("ignitionswitch",true);
        return availableItems;
    }

    public static Map<String,String> getAvailableDirections(){
        availableDirections.put("N","");
        availableDirections.put("S","CapsuleRoom");
        availableDirections.put("E","Kitchen");
        availableDirections.put("W","");
        return availableDirections;
    }
}
