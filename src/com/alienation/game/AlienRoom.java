package com.alienation.game;

import java.util.List;

/**
 * User will find out there is one Alien in this room
 * User has to kill Alien to get the code
 */

public class AlienRoom extends Room {

    /*************** PRIVATE VARIABLE DECLARATIONS  ******************/
    private String initialStory = Engine.ANSI_BLUE + "\n\nYou've entered a nasty smelling room. It smells like rotting flesh and the floor is covered with blood and a slimey substance.\n" +
            "Wait... you see something in the corner slumped over, moving back and forth.\n"+ Engine.ANSI_RESET;
    private String updatedStory = Engine.ANSI_BLUE + "\n\nLast time you were here you fought the alien. It's still in here.... I can smell it.\n"+ Engine.ANSI_RESET;
    private String lastStory = Engine.ANSI_BLUE + "\n\nYou are back in the room where you killed your crew member.... I mean alien. Nothing has changed except the pools of your friends blood.\n" +
            "OOPS... did it again."+ Engine.ANSI_RESET;
    public int count = 0;

    /*************** PUBLIC METHODS  ******************/
    // This method used to load Environment to user
    public void loadEnvironment(){
        count++;
        System.out.println(Banner.getBanner());
        super.loadEnvironment();
        Menu.displayMenu();
    }

    // Get Story line while page loads
    public String getStory() {
        List<String> availableItems = Engine.getAvailableItemsMap().get(Rooms.AlienRoom);
        if (!availableItems.contains("Humanoid")) {
            return getLastStory();
        }else{
            if(count >1 && availableItems.contains("Humanoid")){
                return getUpdatedStory();
            }else if(availableItems.contains("Humanoid")){
                return getInitialStory();
            }
        }

        return null;
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
