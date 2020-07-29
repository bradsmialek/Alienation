package com.alienation.game;

/**
 * Kitchen - This is the room where user can go and investigate
 * User can eat available edible items and increase health points
 */

public class Kitchen extends Room {

    /*************** PRIVATE VARIABLE DECLARATIONS  ******************/
    private String initialStory = Engine.ANSI_BLUE + "\n\nYou've entered the kitchen and you can immediately tell that something has gotten into the food. There are all\n" +
            "types of nasty smells filling the air, but you are so hungry that the smells can't bother you at this point. There must be something\n" +
            "that you can eat laying around here somewhere?\n"+ Engine.ANSI_RESET;
    private String updatedStory = Engine.ANSI_BLUE + "\n\nYou are back in the kitchen where you found that delicious snack. It looks like something's been through here\n" +
            "again.\n"+ Engine.ANSI_RESET;


    /*************** PUBLIC METHODS  ******************/
    // This method used to load Environment to user
    public void loadEnvironment(){
        System.out.println(Banner.getBanner());
        super.loadEnvironment();
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
        return initialStory;
    }

    public String getUpdatedStory() {
        return updatedStory;
    }
}
