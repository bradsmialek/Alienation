package com.alienation.game;

/**
 * Character Class
 */
public class Character {
    public static final int MAX_HEALTH = 100;
    public static final int MIN_HEALTH  = 0;
    private static int health = 100;
    private static String currentWeapon;
    private static String currentRoom = "Room1"; // as initial defalut

    public String getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

}
