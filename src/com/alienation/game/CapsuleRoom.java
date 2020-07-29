package com.alienation.game;

/**
 * Capsule Room - This is the room where the character wakes up
 * User will see this room first when start the game
 */
public class CapsuleRoom extends Room {

    /*************** PRIVATE VARIABLE DECLARATIONS  ******************/
    private String initialStory = Engine.ANSI_BLUE + "\n\nAs you open your eyes your vision is blurry and your body hurts. You gasp to take your first breath as you wake from cryo-sleep.\n" +
            "You can tell the oxygen levels are low as it seems harder to breathe.  As you look around you notice that there is one crew member\n" +
            "missing and their sleeping capsule is shattered with blood splattered across the front. The ship seems to be drifting in\n" +
            "space and the lights are dim, most likely on some sort of backup system. You notice a Taser on the floor.\n"+ Engine.ANSI_RESET;
    private String updatedStory = Engine.ANSI_BLUE + "\n\nYou are back in the Capsule Room. Another crew member is missing. There is a blood trail....\n"+ Engine.ANSI_RESET;
    private String lastStory = Engine.ANSI_BLUE + "\n\nYou are back in the Capsule Room. Nothing has changed. There are crew members missing and blood everywhere."+ Engine.ANSI_RESET;
    public int count = 0;

    /*************** PUBLIC METHODS  ******************/
    // This method used to load Environment to user
    public void loadEnvironment(){
        count++;
        super.loadEnvironment();
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
        return initialStory;
    }

    public String getUpdatedStory() {
        return updatedStory;
    }

    public String getLastStory() {
        return lastStory;
    }
}
