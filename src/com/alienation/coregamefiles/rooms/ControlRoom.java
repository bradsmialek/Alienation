/*
 *      Alienation
 *      TLG Learning: Capstone Project
 *      Originally Created by Team Alienation &&
 *      Edited by Team Capstone Mafia
 *      https://github.com/Capstone-Mafia
 *
 *      Team Alienation Members:
 *      Brad Smialek (https://github.com/bradsmialek)
 *      Dhruti Kosta (https://github.com/dhruti-kosta)
 *      Terrell Douglas (https://github.com/Dougie105)
 *      Original project repo:
 *      https://github.com/bradsmialek/Alienation
 *
 *      Capstone Mafia Members:
 *      Bruce West (https://github.com/BruceBAWest)
 *      Gurinder Batth (https://github.com/GurinderB)
 *      Daeun Lok (https://github.com/koreareefclub)
 *      Capstone Mafia fork:
 *      https://github.com/Capstone-Mafia/Alienation
 */

package com.alienation.coregamefiles.rooms;

import com.alienation.coregamefiles.Character;
import com.alienation.coregamefiles.Menu;
import com.alienation.coregamefiles.gameart.RoomsMap;
import com.alienation.coregamefiles.gameart.Win;

import java.util.concurrent.TimeUnit;

import static com.alienation.coregamefiles.gameart.TextColors.ANSI_BLUE;
import static com.alienation.coregamefiles.gameart.TextColors.ANSI_RESET;

/**
 * Capsule Room - This is the room where the character wakes up
 * User will see this room first when start the game
 */
public class ControlRoom extends Room {
    /*************** PUBLIC METHODS  ******************/
    // This method used to load Environment to user
    public void loadEnvironment() throws Exception {
        super.loadEnvironment();
        System.out.println(RoomsMap.controlRoom());
        if (!Character.getInventory().contains("Ignition Switch")){
            Menu.displayMenu();
        } else {
            try {
                TimeUnit.SECONDS.sleep(4);
                Win.win();
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
        }
    }

    /*************** GETTER - SETTER METHODS  ******************/
    public String getInitialStory() {
        return ANSI_BLUE + "\n\nYou've entered a large room with navigation stations and controls to pilot the ship. The view of the vast dark space around you\n" +
                    "is mesmerizing! You're immediate thought is to send a message for help, but all contact between Earth and you has been disabled.\n" +
                    "Maybe you can pilot the ship back home?  You climb into the pilot ship and you notice the starter panel looks fried. You know\n" +
                    "enough to know that you have to find an ignition switch and pray that works.\n" + ANSI_RESET;
    }

    public String getLastStory() {
        return ANSI_BLUE + "\n\nYou're back in the Control room and you have finally found an ignition switch! You replace the old one and hit the switch, and what\n" +
                    "you hear next is the sweetest sound you've ever heard!  The engines fire up and you enter Earth's coordinates on auto pilot.\n" +
                    "You're finally headed home, the aliens are dead, and the rest of the crew begin to wake up. What will you tell them?\n" + ANSI_RESET;
    }
}
