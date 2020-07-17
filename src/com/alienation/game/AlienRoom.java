package com.alienation.game;

/**
 * Alien Room - This is the room where user can go and investigate
 * User will find out there is one Alien in this room
 * User has to kill Alien to get the code
 */

public class AlienRoom {

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


    /*************** PUBLIC METHODS  ******************/
    public static void loadEnvironment(){
        System.out.println(getInitialStory());
        System.out.println(Menu.getActions());
        System.out.println(Menu.getDirections());
    }


    /*************** GETTER - SETTER METHODS  ******************/
    public static String getInitialStory() {
        if(Engine.getInitialItems().get("alien1") != null){
            if(Engine.getInitialItems().get("alien1")){
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
}
