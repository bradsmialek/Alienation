package com.alienation.coregamefiles;

import com.alienation.enginefiles.Engine;

import static com.alienation.coregamefiles.TextColors.*;

/**
 * Kitchen - This is the room where user can go and investigate
 * User can eat available edible items and increase health points
 */

public class Kitchen extends Room {
    /*************** PUBLIC METHODS  ******************/
    // This method used to load Environment to user
    public void loadEnvironment() throws Exception {
        super.loadEnvironment();
        System.out.println(RoomsMap.kitchenRoom());
        Menu.displayMenu();
    }

    // Get Story line while page loads
    public String getStory() {
        if (!Engine.getAvailableItemsMap().get(Rooms.Kitchen).contains("Snickers")) {
            return getUpdatedStory();
        } else {
            return getInitialStory();
        }
    }

    /*************** GETTER - SETTER METHODS  ******************/
    public String getInitialStory() {
        return ANSI_BLUE + "\n\nYou've entered the kitchen and you can immediately tell that something has gotten into the food. There are all\n" +
                "types of nasty smells filling the air, but you are so hungry that the smells can't bother you at this point. There must be something\n" +
                "that you can eat laying around here somewhere?\n"+ ANSI_RESET;
    }

    public String getUpdatedStory() {
        return ANSI_BLUE + "\n\nYou are back in the kitchen where you found that delicious snack. It looks like something's been through here\n" +
                "again.\n"+ ANSI_RESET;
    }
}
