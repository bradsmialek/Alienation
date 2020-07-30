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

import com.alienation.coregamefiles.Menu;
import com.alienation.coregamefiles.gameart.RoomsMap;

import static com.alienation.coregamefiles.gameart.TextColors.*;

/**
 * Capsule Room - This is the room where the character wakes up
 * User will see this room first when start the game
 */
public class CapsuleRoom extends Room {

    /*************** PRIVATE VARIABLE DECLARATIONS  ******************/
    public int count = 0;

    /*************** PUBLIC METHODS  ******************/
    // This method used to load Environment to user
    public void loadEnvironment() throws Exception {
        count++;
        super.loadEnvironment();
        System.out.println(RoomsMap.capsuleRoom());
        Menu.displayMenu();
    }

    // Get Story line while page loads
    public String getStory() {
        if(count == 1){
            return getInitialStory();
        }
        else if(count == 2){
            return getUpdatedStory();
        }
        else{
            return getLastStory();
        }
    }

    /*************** GETTER - SETTER METHODS  ******************/
    public String getInitialStory() {
        return ANSI_BLUE + "\n\nAs you open your eyes your vision is blurry and your body hurts. You gasp to take your first breath as you wake from cryo-sleep.\n" +
                "You can tell the oxygen levels are low as it seems harder to breathe.  As you look around you notice that there is one crew member\n" +
                "missing and their sleeping capsule is shattered with blood splattered across the front. The ship seems to be drifting in\n" +
                "space and the lights are dim, most likely on some sort of backup system. You notice a Taser on the floor.\n"+ ANSI_RESET;
    }

    public String getUpdatedStory() {
        return ANSI_BLUE + "\n\nYou are back in the Capsule Room. Another crew member is missing. There is a blood trail....\n"+ ANSI_RESET;
    }

    public String getLastStory() {
        return ANSI_BLUE + "\n\nYou are back in the Capsule Room. Nothing has changed. There are crew members missing and blood everywhere."+ ANSI_RESET;
    }
}
