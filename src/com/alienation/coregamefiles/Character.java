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

package com.alienation.coregamefiles;

import com.alienation.coregamefiles.enums.Rooms;

import java.util.ArrayList;
import java.util.List;

import static com.alienation.coregamefiles.gameart.TextColors.*;

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
            System.out.println(ANSI_RED + "\nHealth is LOW!" + ANSI_RESET);
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
