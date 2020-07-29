package com.alienation.game;

//<<<<<<< HEAD
//import org.w3c.dom.ls.LSOutput;
//
//import java.util.HashMap;
//import java.util.Map;
//=======
import java.util.List;
//>>>>>>> 1e088c83802101dcef03e237eaf09f2b3d9c626d

/**
 * User will find out there is one Alien in this room
 * User has to kill Alien to get the code
 */

public class AlienRoom extends Room {

    //<<<<<<< HEAD
//    private static Map<String,Boolean> availableItems = new HashMap<String, Boolean>();
//    private static final Map<String,Rooms> availableDirections = new HashMap<String, Rooms>();
//    private static final int minusOxy = 10;

    /*************** PUBLIC METHODS  ******************/
    // This method used to load Environment to user
    public void loadEnvironment() throws Exception {
//        Character.checkHealth();
//        Oxygen.minOxygen(minusOxy);
//        Oxygen.checkOxy();
//        System.out.println(getStory());
//        System.out.println(RoomsMap.alienRoom());
//=======
//    public int count = 0;

    // This method used to load Environment to user
//    public void loadEnvironment(){
//        count++;
//        System.out.println(Banner.getBanner());
        super.loadEnvironment();
//>>>>>>> 1e088c83802101dcef03e237eaf09f2b3d9c626d
        Menu.displayMenu();
    }

    // Get Story line while page loads
//<<<<<<< HEAD
    public static String getStory() {
//        if (!getAvailableItems().containsKey("Humanoid")) {
//            return lastStory;
//        }else if( Menu.attackCount > 0 && getAvailableItems().containsKey("Humanoid") ){
//            return updatedStory;
//        }
//        else{
//            return initialStory;
//=======
//    public String getStory() {
        List<String> availableItems = Engine.getAvailableItemsMap().get(Rooms.AlienRoom);
        if (!availableItems.contains("Humanoid")) {
            return getLastStory();
        }else if (Menu.attackCount > 0 && availableItems.contains("Humanoid")){
                return getUpdatedStory();
        }else{
            return getInitialStory();
        }
//>>>>>>> 1e088c83802101dcef03e237eaf09f2b3d9c626d
//        }
    }

    /*************** GETTER - SETTER METHODS  ******************/
    public static String getInitialStory() {
        return Engine.ANSI_BLUE + "\n\nYou've entered a nasty smelling room. It smells like rotting flesh and the floor is covered with blood and a slimey substance.\n" +
                    "Wait... you see something in the corner slumped over, moving back and forth.\n" + Engine.ANSI_RESET;
    }

    public static String getUpdatedStory() {
        return Engine.ANSI_BLUE + "\n\nLast time you were here you fought the alien. It's still in here.... I can smell it.\n" + Engine.ANSI_RESET;
    }

    public static String getLastStory() {
        return Engine.ANSI_BLUE + "\n\nYou are back in the room where you killed your crew member.... I mean alien. Nothing has changed except the pools of your friends blood.\n" +
                    "OOPS... did it again." + Engine.ANSI_RESET;
    }
}
