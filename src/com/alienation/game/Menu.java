package com.alienation.game;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
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
    private static String actions = "You can < Investigate, Open, (Eat or Drink), (Grab or Take), (Attack or Fight), (Read), (Swap or Hold), (Run) >";
    private static String directions = "You can move < (N or North), (S or South), (E or East), (W or West) >";
    private static String inv = "Check Inventory < (i or Inventory)>";
    private static Actions action;
    private static String taction;
    private static Edibles edible;
    private static Xitems xItem;
    private static CanOpen itemToOpen;
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
                open(currentRoom);
                break;
            case EAT:
            case DRINK:
                eat(currentRoom);
                break;
            case GRAB:
            case TAKE:
                grab(currentRoom);
                break;
            case FIGHT:
            case ATTACK:
                attack(currentRoom);
                break;
            case READ:
                read();
                break;
            case HOLD:
            case SWAP:
                swap(currentRoom);
                break;
            case NORTH:
            case N:
                moveRoom("N", currentRoom);
                break;
            case EAST:
            case E:
                moveRoom("E", currentRoom);
                break;
            case SOUTH:
            case S:
                moveRoom("S", currentRoom);
                break;
            case WEST:
            case W:
                moveRoom("W", currentRoom);
                break;
            case OPTIONS:
            case O:
                System.out.println("\n" + Engine.ANSI_BLUE + getActions() + "\n" + getDirections() + "\n" + getInv() + Engine.ANSI_RESET);
                Menu.displayMenu();
                break;
            case INVENTORY:
            case I:
                CheckInventory();
                break;
            case RUN:
                run(currentRoom);
                break;
        }

        in.close();
    }



    // TODO: When swapping to squirt_gun or taser_gun you need underscores. Im stuck here, otherwise swap works for everything else
    //swaps weapons
    public static void swap(Rooms currentRoom){
        final String space = "\n";
        final String lines = "************";

        System.out.println(space + Engine.ANSI_YELLOW + "Which weapon would you like to use?\n");
        System.out.println(lines);

        Set<String> keys = Character.getInventory().keySet();
        for (String key : keys) {
            System.out.println(key);
        }
        System.out.println(lines + Engine.ANSI_RESET);
        Scanner in = new Scanner(System.in);

        try {
            answer = in.nextLine(); //grabs input
            Weapons weapon = Weapons.valueOf(answer.toUpperCase()); // input to upper then checks input against ENUMs - implicit

            switch (weapon){
                case FLAMETHROWER:
                    Character.setCurrentWeapon(Weapons.FLAMETHROWER.getName());
                    System.out.println(Engine.ANSI_YELLOW + "\nYou are now holding a " + answer + "." + Engine.ANSI_RESET);
                    break;
                case LASER:
                    Character.setCurrentWeapon(Weapons.LASER.getName());
                    System.out.println(Engine.ANSI_YELLOW + "\nYou are now holding a " + answer + "." + Engine.ANSI_RESET);
                    break;
                case SQUIRTGUN:
                    Character.setCurrentWeapon(Weapons.SQUIRTGUN.getName());
                    System.out.println(Engine.ANSI_YELLOW + "\nYou are now holding a " + answer + "." + Engine.ANSI_RESET);
                    break;
                case TASER:
                    Character.setCurrentWeapon(Weapons.TASER.getName());
                    System.out.println(Engine.ANSI_YELLOW + "\nYou are now holding a " + answer + "." + Engine.ANSI_RESET);
                    break;
                default:
                    System.out.println(Engine.ANSI_RED + "\nYou can't swap with that." + Engine.ANSI_RESET);
                    break;
            }

        } catch (IllegalArgumentException e) {
            System.out.println(Engine.ANSI_RED + "\nYou can'tt swap with that." + Engine.ANSI_RESET);
        }
        Menu.displayMenu();
    }




    //read clues
    public static void read(){
        System.out.println("Can't Read yet!!");
        //TODO: Read
        //
    }

    // Run from alien to previous room
    public static void run(Rooms currentRoom) {
        Map<String, Boolean> availableItems = new HashMap<>();
        Map<String, Boolean> availableAliens = new HashMap<>();
        switch (currentRoom) {
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
                availableItems = SupplyRoom.getAvailableItems();
                break;
            case ControlRoom:
                availableItems = ControlRoom.getAvailableItems();
                break;
        }
        availableAliens = Alien.getAliens();
        Set<String> aliens = availableAliens.keySet();
        Set<String> keysInRoom = availableItems.keySet();
        boolean reply = false;
        for (String key : keysInRoom) {
            for(String alien : aliens){
                if(key.equals(alien)){
                    System.out.println(Engine.ANSI_RED + "\nYou ran away as fast as you can!" + Engine.ANSI_RESET);
                    loadRoom(Character.getPreviousRoom());
                }else{
                    reply = true;
                }
            }
        }
        if(reply){
            System.out.println(Engine.ANSI_RED + "\nYou can only run from an alien scaredy pants!" + Engine.ANSI_RESET);
        }
        Menu.displayMenu();
    }

    // Investigate the room //
    public static void attack(Rooms currentRoom){
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
                availableItems = SupplyRoom.getAvailableItems();
                break;
            case ControlRoom:
                availableItems = ControlRoom.getAvailableItems();
                break;
        }
        final String lines = "************";
        System.out.println(space + Engine.ANSI_YELLOW + action + " what?\n");
        System.out.println(lines);
        Set<String> keys = availableItems.keySet();
        for (String key : keys) {
            System.out.println(key);
        }
        System.out.println(lines + Engine.ANSI_RESET);
        for (String key : keys) {
            if(Alien.getAliens().containsKey(key)){
                System.out.println("Build out attack functionality");

                //TODO: create attack logic

                //attack or run
                //if attack?    type of weapon dealing damage?  Look at Weapon Enums
                //if run?  call run () should go back to previous room and show menu
                //set alien health after attack
                //alien retaliates .. get type and damage dealt
                //set character hp
                //delete alien on death  & add code to room items
                //Other stuff for sure!!

            }
        }
        Menu.displayMenu();
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
                availableItems = SupplyRoom.getAvailableItems();
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
            if(Alien.getAliens().containsKey(key)){
                switch (key){
                    case "Human":
                        System.out.println(Engine.ANSI_BLUE + "\nIt looks like you found your crew mate, they look dismembered and there is a large bloody hole in their chest.\n"+
                                "You can see their insides squirming around, their eyes are black with bloody tears leaking from the corners. They notice you and it let's\n"+
                                "out a horrific bellowing growl. This is not your crew mate anymore ... it's coming to get you!!\n"+ Engine.ANSI_RESET);
                        break;
                    case "Rat":
                        System.out.println(Engine.ANSI_BLUE + "\nIt's a rat alien\n"+ Engine.ANSI_RESET);
                        break;
                    case "Dog":
                        System.out.println(Engine.ANSI_BLUE + "\nIt's a dog alien\n"+ Engine.ANSI_RESET);
                        break;
                    case "Unidentifiable":
                        System.out.println(Engine.ANSI_BLUE + "\nIt's an Ultimate alien\n"+ Engine.ANSI_RESET);
                        break;
                }
            }
        }
        Menu.displayMenu();
    }

    //Open something
    public static void open(Rooms currentRoom) {
        final String space = "\n";
        Map<String, Boolean> availableItems = new HashMap<>();
        switch (currentRoom) {
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
                availableItems = SupplyRoom.getAvailableItems();
                break;
            case ControlRoom:
                availableItems = ControlRoom.getAvailableItems();
                break;
        }
        final String lines = "************";
        System.out.println(space + Engine.ANSI_YELLOW + "Open what?\n");
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

        //Check if user response is in the room?
        if(items.contains(newAnswer)) {
            //check if item can be opened against enums
            try {
                itemToOpen = CanOpen.valueOf(newAnswer.toUpperCase());
                System.out.println(itemToOpen);
                String upperAnswer = newAnswer.toUpperCase();
                if (itemToOpen.toString().equals(upperAnswer)) {
                    System.out.println("pass... still working on it");
                    if(!Character.getInventory().containsKey(newAnswer)){
                        System.out.println("It's locked");
                    }else{
                        System.out.println("New item added to inventory.");
                        Map<String,String> newItems = new HashMap<>();
                        newItems = Character.getInventory();
                        newItems.put(newAnswer, "reply");
                        // delete item from room
                        availableItems.remove(newAnswer);
                    }
                } else {
                    System.out.println("here");
                    Menu.displayMenu();
                }
            } catch (IllegalArgumentException e) {
                System.out.println(Engine.ANSI_RED + "\nYou can't open that!" + Engine.ANSI_RESET);
            }
        }else{
            System.out.println("That's not in this room.");
        }
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
                availableItems = SupplyRoom.getAvailableItems();
                break;
            case ControlRoom:
                availableItems = ControlRoom.getAvailableItems();
                break;
        }
        final String lines = "************";
        System.out.println(space + Engine.ANSI_YELLOW + action + " what?\n");
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

            //TODO: check against enums... hard coded for now
            if(newAnswer.equals("Taser")){
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
                availableItems = SupplyRoom.getAvailableItems();
                break;
            case ControlRoom:
                availableItems = ControlRoom.getAvailableItems();
                break;
        }
        System.out.println(space + Engine.ANSI_YELLOW + action + " what?\n");

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
                SupplyRoom.setAvailableItems(availableItems);
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
            Character.setPreviousRoom(currentRoom);
            Character.setTempRoom(currentRoom);
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
                nextRoom = SupplyRoom.getAvailableDirections().get(direction);
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
                SupplyRoom.loadEnvironment();
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

