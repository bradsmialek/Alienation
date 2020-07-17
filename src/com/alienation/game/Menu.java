package com.alienation.game;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Scanner;

/**
 * Menu For Console
 * This class used to display Menu items to User and call related methods for actions on selected verbs.
 */
public class Menu {

    /*************** PRIVATE VARIABLE DECLARATIONS  ******************/
    private static String actionQuestion = "What would you like to do?\n" + ">";
    private static String actions = "You can: Investigate, Open, Eat, Grab, Attack, Read, Swap";
    private static String directions = "You can move: N, S, E, W";
    private static Actions action;


    /*************** PUBLIC METHODS  ******************/
    // This method used to display Menu to user
    public static void displayMenu() throws IllegalArgumentException {
        boolean repeat = true;
        Scanner in = new Scanner(System.in);

        System.out.println("Health: " + Character.getHealth() + ", Oxygen: " + Oxygen.getOxygen()  + ", Weapon: " + Character.getCurrentWeapon());
        System.out.println("\n" + getActions());
        System.out.println(getDirections());
        System.out.println("\n" + getActionQuestion());

        while (repeat) {
            try {
                String answer = in.nextLine(); //grabs input
                action = Actions.valueOf(answer.toUpperCase()); // input to upper then checks input against ENUMs - implicit
                repeat = false;
            } catch (IllegalArgumentException e) {
                System.out.println("You must enter one of the following actions: " +
                        java.util.Arrays.asList(Actions.values()));
                repeat = true;
            }
        }

        Rooms currentRoom = Character.getCurrentRoom();
        Rooms nextRoom = null;

        // INVESTIGATE, OPEN, EAT, GRAB, ATTACK, READ, SWAP, N, E, S, W
        switch (action) {
            case INVESTIGATE:
                CapsuleRoom.loadEnvironment();
                break;
            case OPEN:
                System.out.println("do Something with open");
                break;
            case EAT:
                System.out.println("do Something with eat");
                break;
            case GRAB:
                System.out.println("do Something with grab");
                break;
            case ATTACK:
                System.out.println("do Something with attack");
                break;
            case READ:
                System.out.println("do Something with read");
                break;
            case SWAP:
                System.out.println("do Something with swap");
                break;
            case N:
                nextRoom = getRoom("N", currentRoom);
                if(nextRoom != null){
                    loadRoom(nextRoom);
                }
                else{
                    System.out.println("You cannot go to that side.");
                    displayMenu();
                }
                break;
            case E:
                nextRoom = getRoom("E", currentRoom);
                if(nextRoom != null){
                    loadRoom(nextRoom);
                }
                else{
                    System.out.println("You cannot go to that side.");
                    displayMenu();
                }
                break;
            case S:
                nextRoom = getRoom("S", currentRoom);
                if(nextRoom != null){
                    loadRoom(nextRoom);
                }
                else{
                    System.out.println("You cannot go to that side.");
                    displayMenu();
                }
                break;
            case W:
                nextRoom = getRoom("W", currentRoom);
                if(nextRoom != null){
                    loadRoom(nextRoom);
                }
                else{
                    System.out.println("You cannot go to that side.");
                    displayMenu();
                }
                break;
        }

        in.close();
    }

    public static Rooms getRoom(String direction, Rooms currentRoom){
        Rooms nextRoom = null;
        switch (currentRoom){
            case CapsuleRoom:
                nextRoom = CapsuleRoom.getAvailableDirections().get(direction);
                break;
            case AlienRoom:
                nextRoom = AlienRoom.getAvailableDirections().get(direction);
                break;
            case Kitchen:
                nextRoom = Kitchen.getAvailableDirections().get(direction);
                break;
            case ComputerRoom:
                nextRoom = ComputerRoom.getAvailableDirections().get(direction);
                break;
            case ControlRoom:
                nextRoom = ControlRoom.getAvailableDirections().get(direction);
                break;
        }
        return nextRoom;
    }

    public static void loadRoom(Rooms newRoom){
        Character.setCurrentRoom(newRoom);
        switch (newRoom){
            case CapsuleRoom:
                CapsuleRoom.loadEnvironment();
                break;
            case AlienRoom:
                AlienRoom.loadEnvironment();
                break;
            case Kitchen:
                Kitchen.loadEnvironment();
                break;
            case ComputerRoom:
                ComputerRoom.loadEnvironment();
                break;
            case ControlRoom:
                ControlRoom.loadEnvironment();
                break;
        }
    }


    /*************** GETTER - SETTER METHODS  ******************/
    public static String getActionQuestion() {
        return actionQuestion;
    }

    public static String getActions() {
        return actions;
    }

    public static String getDirections() {
        return directions;
    }
}

