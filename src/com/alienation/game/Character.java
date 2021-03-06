package com.alienation.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Character Class
 * This class used for maintaining characteristics for character like health points, current weapon.
 */
public class Character {

    /*************** PRIVATE VARIABLE DECLARATIONS  ******************/
    public static final int MAX_HEALTH = 100;
    public static final int MIN_HEALTH = 0;
    private static int health = 5;
    private static String currentWeapon = "Bad breath";
    private static Rooms currentRoom = Rooms.CapsuleRoom; // as initial default
    private static Rooms previousRoom;
    private static Rooms tempRoom;
    private static List<String> inventory = new ArrayList<>();


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

    public static void checkHealth(){
        if(health <= 20){
            System.out.println(Engine.ANSI_RED + "\nHealth is LOW!" + Engine.ANSI_RESET);
        }
    }

    public static String getCurrentWeapon() {
        return currentWeapon;
    }

    public static void  setCurrentWeapon(String newCurrentWeapon) {
        currentWeapon = newCurrentWeapon;
    }

    public static Rooms getCurrentRoom() {
        return currentRoom;
    }

    public static void setCurrentRoom(Rooms newCurrentRoom) {
        currentRoom = newCurrentRoom;
    }

    // Get available items in inventory
    public static List<String> getInventory(){
        return inventory;
    }

    // set available items updated items if item moved to Inventory or Eat an item
    public static void setInventory(List<String> newInventory) {
        inventory = newInventory;
    }

    public static Rooms getPreviousRoom() {
        return previousRoom;
    }

    public static void setPreviousRoom(Rooms tempRoom) {
        previousRoom = tempRoom;
    }

    public static Rooms getTempRoom() {
        return tempRoom;
    }

    public static void setTempRoom(Rooms currentRoom) {
        tempRoom = currentRoom;
    }
}
