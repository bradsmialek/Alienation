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

import static com.alienation.coregamefiles.gameart.TextColors.*;

/**
 * Supply room
 * Room has a cage that keeps expensive and rare parts locked up.
 * Cage needs a code to open and it has "Ignition switch" that can be useful to fly back home
 * Use the code you got from Alien Room by killing Alien.
 */

public class SupplyRoom extends Room {
    /*************** PUBLIC METHODS  ******************/
    // This method used to load Environment to user
    public void loadEnvironment() throws Exception {
        super.loadEnvironment();
        System.out.println(RoomsMap.supplyRoom());
        Menu.displayMenu();
    }

    // Get Story line while page loads
    //check for items
    public String getStory() {
        if (!Character.getInventory().contains("Code")) { //if inv does not contain code
            return getInitialStory();
        } else {
            if(!Character.getInventory().contains("Ignition Switch")){ //has code, no ignition
                return getLastStory();
            }
            return getUpdatedStory();
        }
    }

    /*************** GETTER - SETTER METHODS  ******************/
    public String getInitialStory() {
        return ANSI_BLUE + "\n\nYou've entered a dim room with racks of common supplies. This must be the supply room.  There's a big metal\n" +
                "cage where all the rare and expensive parts are kept, but it's locked. The only way to open it is at the computer terminal with\n" +
                "an access code. Something is dripping onto your shoulder... some sort of slimey viscous substance. It looks like one computer still\n" +
                "works, but you need an access code.\n" + ANSI_RESET;
    }

    public String getUpdatedStory() {
        return ANSI_BLUE + "\n\nYou're back in the Server room and you have found an ignition switch. This thing must do something, but what!?\n"+ ANSI_RESET;
    }

    public String getLastStory() {
        return ANSI_BLUE + "\n\nYou are back in the Server Room. You have the access code to open the cage. You enter the code into the terminal and\n" +
                "you hear metal grinding on metal and then a loud click."+ ANSI_RESET;
    }
}
