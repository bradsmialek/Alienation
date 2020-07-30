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
import com.alienation.coregamefiles.enums.Rooms;
import com.alienation.coregamefiles.gameart.RoomsMap;
import com.alienation.enginefiles.Engine;

import java.util.List;

import static com.alienation.coregamefiles.gameart.TextColors.ANSI_BLUE;
import static com.alienation.coregamefiles.gameart.TextColors.ANSI_RESET;



/**
 * User will find out there is one Alien in this room
 * User has to kill Alien to get the code
 */

public class AlienRoom extends Room {

    /*************** PUBLIC METHODS  ******************/
    // This method used to load Environment to user
    public void loadEnvironment() throws Exception {
        super.loadEnvironment();
        System.out.println(RoomsMap.alienRoom());
        Menu.displayMenu();
    }

    // Get Story line while page loads
    public String getStory() {
        List<String> availableItems = Engine.getAvailableItemsMap().get(Rooms.AlienRoom);
        if (!availableItems.contains("Humanoid")) {
            return getLastStory();
        }else if (Menu.attackCount > 0 && availableItems.contains("Humanoid")){
                return getUpdatedStory();
        }else{
            return getInitialStory();
        }
    }

    /*************** GETTER - SETTER METHODS  ******************/
    public String getInitialStory() {
        return ANSI_BLUE + "\n\nYou've entered a nasty smelling room. It smells like rotting flesh and the floor is covered with blood and a slimey substance.\n" +
                    "Wait... you see something in the corner slumped over, moving back and forth.\n" + ANSI_RESET;
    }

    public String getUpdatedStory() {
        return ANSI_BLUE + "\n\nLast time you were here you fought the alien. It's still in here.... I can smell it.\n" + ANSI_RESET;
    }

    public String getLastStory() {
        return ANSI_BLUE + "\n\nYou are back in the room where you killed your crew member.... I mean alien. Nothing has changed except the pools of your friends blood.\n" +
                    "OOPS... did it again." + ANSI_RESET;
    }
}
