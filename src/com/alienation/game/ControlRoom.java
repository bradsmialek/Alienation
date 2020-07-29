package com.alienation.game;

import java.util.concurrent.TimeUnit;

/**
 * Capsule Room - This is the room where the character wakes up
 * User will see this room first when start the game
 */
public class ControlRoom extends Room {

    /*************** PRIVATE VARIABLE DECLARATIONS  ******************/
    private String initialStory = Engine.ANSI_BLUE + "\n\nYou've entered a large room with navigation stations and controls to pilot the ship. The view of the vast dark space around you\n" +
            "is mesmerizing! You're immediate thought is to send a message for help, but all contact between Earth and you has been disabled.\n" +
            "Maybe you can pilot the ship back home?  You climb into the pilot ship and you notice the starter panel looks fried. You know\n" +
            "enough to know that you have to find an ignition switch and pray that works.\n"+ Engine.ANSI_RESET;
    private String lastStory = Engine.ANSI_BLUE + "\n\nYou're back in the Control room and you have finally found an ignition switch! You replace the old one and hit the switch, and what\n" +
            "you hear next is the sweetest sound you've ever heard!  The engines fire up and you enter Earth's coordinates on auto pilot.\n" +
            "You're finally headed home, the aliens are dead, and the rest of the crew begin to wake up. What will you tell them?\n"+ Engine.ANSI_RESET;
    //TODO : add timeout END GAME and ps.  You feel movement in your belly...

    /*************** PUBLIC METHODS  ******************/
    // This method used to load Environment to user
    public void loadEnvironment(){
        System.out.println(Banner.getBanner());
        super.loadEnvironment();
        if (!Character.getInventory().contains("Ignition Switch")){
            Menu.displayMenu();
        } else {
            try {
                TimeUnit.SECONDS.sleep(4);
                Menu.win(); // TODO : ASCII for WIN
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Get Story line while page loads
    // check inventory for part
    public String getStory() {
        if (!Character.getInventory().contains("Ignition Switch")) { //if inventory does not contain ignition
            return getInitialStory();
        } else{
            return getLastStory();

            //TODO:  end game scene on timeout... then exit system... or go to start game scene??
        }
    }

    /*************** GETTER - SETTER METHODS  ******************/
    public String getInitialStory() {
        return initialStory;
    }

    public String getLastStory() {
        return lastStory;
    }
}
