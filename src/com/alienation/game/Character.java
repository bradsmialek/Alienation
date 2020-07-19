package com.alienation.game;

/**
 * Character Class
 * This class used for maintaining characteristics for character like health points, current weapon.
 */
public class Character {

    /*************** PRIVATE VARIABLE DECLARATIONS  ******************/
    public static final int MAX_HEALTH = 100;
<<<<<<< HEAD
    public static final int MIN_HEALTH  = 0;
    private static int health = 100;
    private static String currentWeapon;
    private static String currentRoom = "CapsuleRoom"; // as initial defalut

    public static String getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }
=======
    public static final int MIN_HEALTH = 0;
    private static int health = 50;
    private static String currentWeapon = null;
    private static Rooms currentRoom = Rooms.CapsuleRoom; // as initial default
>>>>>>> 9a21e2f43bed4251c360886f6907a1c6edd2a434


    /*************** GETTER - SETTER METHODS  ******************/
    public static int getHealth() {
        return health;
    }

    public static void setHealth(int newHealth) {
        if ((health + newHealth) > MAX_HEALTH) {
            health = MAX_HEALTH;
        } else if((Character.health + newHealth) < MIN_HEALTH) {
            health = MIN_HEALTH;
        } else {
            health += newHealth;
        }
    }

<<<<<<< HEAD
}
=======
    public static String getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(String newCurrentWeapon) {
        currentWeapon = newCurrentWeapon;
    }

    public static Rooms getCurrentRoom() {
        return currentRoom;
    }

    public static void setCurrentRoom(Rooms newCurrentRoom) {
        currentRoom = newCurrentRoom;
    }
}
>>>>>>> 9a21e2f43bed4251c360886f6907a1c6edd2a434
