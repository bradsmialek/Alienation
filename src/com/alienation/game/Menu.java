package com.alienation.game;
import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

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
    private static Edibles edible;
    private static Xitems xItem;
    private static String answer;
    private static final String oxygen = "O\u2082"; // O₂

    /*************** PUBLIC METHODS  ******************/
    // This method used to display Menu to user
    public static void displayMenu() throws IllegalArgumentException {
        final String green = Engine.ANSI_GREEN;
        final String end = Engine.ANSI_RESET;
        final String lines = "---------------------------------------------------------------------------------------------------------------------------------";
        final String space = "                                      ";
        System.out.println("\n" + getActionQuestion() + "   " + space + "[HP " + green + Character.getHealth() + end + "   " + oxygen + " " + green  + Oxygen.getOxygen() + end + "   Wpn: " + Engine.ANSI_BLUE + Character.getCurrentWeapon() + end  + "]");
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
        Rooms nextRoom = null;

        // INVESTIGATE, OPEN, EAT, GRAB, ATTACK, READ, SWAP, N, E, S, W, O, I
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
                break;
            case I:
                CheckInventory();
                break;
            case RUN:
                System.out.println("You are running Forest");
                //run();//TODO: create run
                break;
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
        System.out.println(space + Engine.ANSI_YELLOW + "You see:\n");
        System.out.println(lines);
        Set<String> keys = availableItems.keySet();
        for (String key : keys) {
            System.out.println(key);
        }
        System.out.println(lines + Engine.ANSI_RESET);
        for (String key : keys) {
            if(key.equals("Alien")){
                System.out.println(Engine.ANSI_BLUE + "\nYou have encountered Your crew member is dismembered and there is a large bloody hole in their chest.\n"+
                        "You can see their insides squirming around, their eyes are black with bloody tears leaking from the corners. They notice you and it let's\n"+
                        "out a horrific bellowing growl. This is not your crew mate anymore ... it's coming to get you!!\n"+ Engine.ANSI_RESET);
                battleScenes();
        }

        }
        Menu.displayMenu();
    }

    public static void battleScenes(){
        System.out.println("This is where you can attack and run");
        System.out.println("This is programming dead end guys!!");
        //TODO: create attack logic
        //type of weapon dealing damage?
        //attack or run
        //if attack?
        //if run?
        //set alien health after attack
        //delete alien on death
        //Other stuff for sure!!
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
        System.out.println(space + Engine.ANSI_YELLOW + "Grab what?\n");
        System.out.println(lines);
        Set<String> keys = availableItems.keySet();
        for (String key : keys) {
            System.out.println(key);
        }
        System.out.println(lines + Engine.ANSI_RESET);
        Scanner in = new Scanner(System.in);
        String answer = in.nextLine();
        String newAnswer = capitalizeAll(answer);
        Set<String> items = availableItems.keySet();


        //Check if user response is in the room? We can't store anything ya know!
        //TODO: fix check against Enums with underscore words...  pilot seat   PILOT_SEAT ...  still adds these??
        if(items.contains(newAnswer)){
            try {
                xItem = Xitems.valueOf(newAnswer.toUpperCase());
                String upperAnswer = newAnswer.toUpperCase();
                if (xItem.toString().equals(upperAnswer)){
                    System.out.println(Engine.ANSI_RED + "\nYou can't grab that!" + Engine.ANSI_RESET);
                    Menu.displayMenu();
                }else{
                    System.out.println("false");
                }
            }
            catch(IllegalArgumentException e){
                System.out.println();
            }

            if(newAnswer.equals("Oxygen Tank")){
                Oxygen.incOxygen(100);
                System.out.println(Engine.ANSI_YELLOW + "\nOxygen levels are now full.  " + oxygen + " ++" + Engine.ANSI_RESET);
                availableItems.remove(newAnswer);
                Menu.displayMenu();
            }

            //TODO: HARDCODED WEAPON CHECK
            if(newAnswer.equals("Taser Gun")){ // CHECK ENUMS
                Character.setCurrentWeapon(newAnswer);
                System.out.println(Engine.ANSI_YELLOW + newAnswer  + " equipped." + Engine.ANSI_RESET);
            }

            System.out.println(Engine.ANSI_YELLOW + "\n" + newAnswer + " added to Inventory." + Engine.ANSI_RESET);
            Map<String,String> newItems = new HashMap<>();
            newItems = Character.getInventory();
            newItems.put(newAnswer, "reply");

            // delete item from room
            availableItems.remove(newAnswer);
        }else{
            System.out.println(Engine.ANSI_RED + "\nYou can't grab that!" + Engine.ANSI_RESET);
        }

        Menu.displayMenu();
    }

    //TODO: Find a way to add more than 1 of same item maybe?

    // Eat the item from the room
    public static void eat(Rooms currentRoom){
        final String space = "\n";
        final String lines = "************";
        boolean repeat = true;

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
        System.out.println(space + Engine.ANSI_YELLOW + "Eat what?\n");

        System.out.println(lines);
        Set<String> keys = availableItems.keySet();
        for (String key : keys) {
            System.out.println(key);
        }
        System.out.println(lines + Engine.ANSI_RESET);
        Scanner in = new Scanner(System.in);

        //TODO: OPTIONS FOR INVENTORY OR ROOM
        try {
            answer = in.nextLine(); //grabs input
            edible = Edibles.valueOf(answer.toUpperCase()); // input to upper then checks input against ENUMs - implicit

            int edibleItems = 0;
            Set<String> items = availableItems.keySet();

            for(Edibles edible : Edibles.values()){
                if(items.contains(edible.getName())){
                    edibleItems++;
                    System.out.println(Engine.ANSI_YELLOW + "\nYou ate " + answer + ".  HP ++" + Engine.ANSI_RESET);
                    int healthPoints = ((Edibles)edible).getHealthPoints();
                    //Increase health points
                    Character.setHealth(healthPoints);
                    //Remove from available items of room
                    availableItems.remove(edible.getName());

                }
            }
            if(edibleItems == 0){
                System.out.println(Engine.ANSI_RED + "There is nothing to eat!!" + Engine.ANSI_RESET);
            }
            updateItems(currentRoom, availableItems);
        } catch (IllegalArgumentException e) {
            System.out.println(Engine.ANSI_RED + "\nYou can't eat that." + Engine.ANSI_RESET);
        }
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
            System.out.println(Engine.ANSI_RED + "\nThere doesn't seem to be a door this way.\n" + Engine.ANSI_RESET);
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
        System.out.println(space + Engine.ANSI_YELLOW + "Inventory\n");
        System.out.println(lines);
        Set<String> keys = inventory.keySet();
        for (String key : keys) {
            System.out.println(key);
        }
        System.out.println(lines + Engine.ANSI_RESET);
        Menu.displayMenu();
    }

    // utility function to capitalize first letter of each word
    public static String capitalizeAll(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        return Pattern.compile("\\b(.)(.*?)\\b")
                .matcher(str)
                .replaceAll(match -> match.group(1).toUpperCase() + match.group(2));
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

