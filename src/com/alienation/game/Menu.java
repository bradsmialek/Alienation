package com.alienation.game;
import org.w3c.dom.ls.LSOutput;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Menu For Console
 * This class used to display Menu items to User and call related methods for actions on selected verbs.
 */
public class Menu {

    /*************** PRIVATE VARIABLE DECLARATIONS  ******************/
    private static String actionQuestion = "What would you like to do? (o for options)";
    private static String actions = "You can < Investigate, Open, Eat, Grab, Attack, Read, Swap >";
    private static String directions = "You can move < N, S, E, W >";
    private static String inv = "Check Inventory < i >";
    private static Actions action;

    /*************** PUBLIC METHODS  ******************/
    // This method used to display Menu to user
    public static void displayMenu() throws IllegalArgumentException {
        final String green = Engine.ANSI_GREEN;
        final String end = Engine.ANSI_RESET;
        final String oxygen = "O\u2082"; // O₂
        final String lines = "---------------------------------------------------------------------------------------------------------------------------------";
        final String space = "                                      ";
        System.out.println("\n" + getActionQuestion() + "   " + space + "[HP " + green + Character.getHealth() + end + "   " + oxygen + " " + green  + Oxygen.getOxygen() + end  + "   Wpn: " + Character.getCurrentWeapon() + "]");
        System.out.println(lines);

        boolean repeat = true;
        Scanner in = new Scanner(System.in);

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
        //System.out.println(currentRoom);
        Rooms nextRoom = null;

        // INVESTIGATE, OPEN, EAT, GRAB, ATTACK, READ, SWAP, N, E, S, W
        switch (action) {
            case INVESTIGATE:
                investigate(currentRoom);
                break;
            case OPEN:
                System.out.println("do Something with open");
                break;
            case EAT:
                eat(currentRoom);
                break;
            case GRAB:
                grab(currentRoom);
                break;
            case ATTACK:
                System.out.println("do Something with attack");
                break;
            case READ:
                System.out.println("Nothing to Read!!");
                break;
            case SWAP:
                System.out.println("Nothing to Swap!!");
                break;
            case N:
                moveRoom("N", currentRoom);
                break;
            case E:
                moveRoom("E", currentRoom);
                break;
            case S:
                moveRoom("S", currentRoom);
                break;
            case W:
                moveRoom("W", currentRoom);
                break;
            case O:
                System.out.println("\n" + Engine.ANSI_BLUE + getActions() + "\n" + getDirections() + "\n" + getInv() + Engine.ANSI_RESET);
                Menu.displayMenu();
            case I:
                CheckInventory();
        }

        in.close();
    }

    // Investigate the room
    public static void investigate(Rooms currentRoom){
        final String space = "\n";
        Map<String,Boolean> availableItems = new HashMap<>();
        switch (currentRoom){
            case CapsuleRoom:
                availableItems = CapsuleRoom.getAvailableItems();
                break;
            case AlienRoom:
                availableItems = AlienRoom.getAvailableItems();
                break;
            case Kitchen:
                availableItems = Kitchen.getAvailableItems();
                break;
            case ComputerRoom:
                availableItems = ComputerRoom.getAvailableItems();
                break;
            case ControlRoom:
                availableItems = ControlRoom.getAvailableItems();
                break;
        }
        final String lines = "************";
        System.out.println(space + Engine.ANSI_YELLOW + "You see:");
        System.out.println(lines);
        Set<String> keys = availableItems.keySet();
        for (String key : keys) {
            System.out.println(key);
        }
        System.out.println(lines + Engine.ANSI_RESET);
//        System.out.println("\n");
        Menu.displayMenu();
    }

    // Grab the item from the room
    public static void grab(Rooms currentRoom){
        final String space = "\n";
        Map<String,Boolean> availableItems = new HashMap<>();
        switch (currentRoom){
            case CapsuleRoom:
                availableItems = CapsuleRoom.getAvailableItems();
                break;
            case AlienRoom:
                availableItems = AlienRoom.getAvailableItems();
                break;
            case Kitchen:
                availableItems = Kitchen.getAvailableItems();
                break;
            case ComputerRoom:
                availableItems = ComputerRoom.getAvailableItems();
                break;
            case ControlRoom:
                availableItems = ControlRoom.getAvailableItems();
                break;
        }
        final String lines = "************";
        System.out.println(space + Engine.ANSI_YELLOW + "Grab what?");
        System.out.println(lines);
        Set<String> keys = availableItems.keySet();
        for (String key : keys) {
            System.out.println(key);
        }
        System.out.println(lines + Engine.ANSI_RESET);
        Scanner in = new Scanner(System.in);
        String answer = in.nextLine();

        //TODO: check verb
//        if edible, weapon, item, else


        Map<String,String> items = new HashMap<>();
        items.put(answer, "true");
        Character.setInventory(items);



        Menu.displayMenu();
    }

    // Eat the item from the room
    public static void eat(Rooms currentRoom){
        Map<String,Boolean> availableItems = new HashMap<>();
        switch (currentRoom){
            case CapsuleRoom:
                availableItems = CapsuleRoom.getAvailableItems();
                break;
            case AlienRoom:
                availableItems = AlienRoom.getAvailableItems();
                break;
            case Kitchen:
                availableItems = Kitchen.getAvailableItems();
                break;
            case ComputerRoom:
                availableItems = ComputerRoom.getAvailableItems();
                break;
            case ControlRoom:
                availableItems = ControlRoom.getAvailableItems();
                break;
        }
        int edibleItems = 0;
        Set<String> items = availableItems.keySet();
        for(Edibles edible : Edibles.values()){
            if(items.contains(edible.getName())){
                edibleItems++;
                int healthPoints = ((Edibles)edible).getHealthPoints();
                //Increase health points
                Character.setHealth(healthPoints);
                //Remove from available items of room
                availableItems.remove(edible.getName());
            }
        }
        if(edibleItems == 0){
            System.out.println("There is nothing to eat!!");
        }
        updateItems(currentRoom, availableItems);
        System.out.println("You ate ");
        Menu.displayMenu();
    }

    // Update available items in room's HashMap
    public static void updateItems(Rooms currentRoom,Map<String,Boolean> availableItems) {
        switch (currentRoom) {
            case CapsuleRoom:
                CapsuleRoom.setAvailableItems(availableItems);
                break;
            case AlienRoom:
                AlienRoom.setAvailableItems(availableItems);
                break;
            case Kitchen:
                Kitchen.setAvailableItems(availableItems);
                break;
            case ComputerRoom:
                ComputerRoom.setAvailableItems(availableItems);
                break;
            case ControlRoom:
                ControlRoom.setAvailableItems(availableItems);
                break;
        }
    }

    // Move Room from one to another
    public static void moveRoom(String direction, Rooms currentRoom){
        Rooms nextRoom = getRoom(direction, currentRoom);
        if(nextRoom != null){
            loadRoom(nextRoom);
        }
        else{
            System.out.println("There doesn't seem to be a door this way.\n");
            displayMenu();
        }
    }

    // Get the next room
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

    // Load the next room
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

    // Get available items of a room
    public static void CheckInventory(){
        final String space = "\n";
        Map<String,String> inventory = new HashMap<>();
        inventory = Character.getInventory();

        final String lines = "************";
        System.out.println(space + Engine.ANSI_YELLOW + "You have:");
        System.out.println(lines);
        Set<String> keys = inventory.keySet();
        for (String key : keys) {
            System.out.println(key);
        }
        System.out.println(lines + Engine.ANSI_RESET);
        Menu.displayMenu();
    }

    // Checks item for appropriate action verb
    public static String checkAction(String toCheck){
        return "";
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

    public static String getInv(){
        return inv;
    }
}

