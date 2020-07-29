package com.alienation.game;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * Menu For Console
 * This class used to display Menu items to User and call related methods for actions on selected verbs.
 */
public class Menu {

    /*************** PRIVATE VARIABLE DECLARATIONS  ******************/
    private static String actionQuestion = "What will you do? (o for options)";
    private static String actions = "Try : look, open item , eat item, grab item, attack, read, swap, run\n";
    private static String directions = "Try : N, north, S, South, e, W, west to move around\n";
    private static String inv = "Check Inventory < i >";
    public static Actions action;
    private static String saveGame = "Save the Game < save >";
    private static String taction;
    private static Edibles edible;
    private static Xitems xItem;
    private static CanOpen itemToOpen;
    private static String answer;
    private static String item1;
    private static String item2;
    private static final String oxygen = "O\u2082"; // O₂


    /*************** PUBLIC METHODS  ******************/
    // This method used to display Menu to user
    public static void displayMenu() throws IllegalArgumentException {
        final String green = Engine.ANSI_GREEN;
        final String end = Engine.ANSI_RESET;
        final String lines = "---------------------------------------------------------------------------------------------------------------------------------";
        final String space = "                                      ";

        System.out.println("\n" + getActionQuestion() + "   " + space + "[HP " + green + Character.getHealth() + end +
                "   " + oxygen + " " + green  + Oxygen.getOxygen() + end + "   Wpn: " + Engine.ANSI_BLUE +
                Character.getCurrentWeapon() + end  + "]");
        System.out.println(lines);

        boolean repeat = true;

        while (repeat) {
            try {
                Input.getInput();
                action = Actions.valueOf(Input.getActionInput().toUpperCase());
                repeat = false;
            } catch (IllegalArgumentException e) {
                System.out.println(Engine.ANSI_RED + "Enter something." + Engine.ANSI_RESET);
                repeat = true;
            }
        }
        Rooms currentRoom = Character.getCurrentRoom();
        Rooms nextRoom = null;

        // Action verbs... things the character can do
        switch (action) {
            case INVESTIGATE:
            case SEE:
            case LOOK:
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
            case GET:
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
            case EQUIP:
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
                System.out.println("\n" + Engine.ANSI_BLUE + getActions() + "\n" + getDirections() + "\n" + getInv() +
                        "\n" + getSaveGame() + Engine.ANSI_RESET);
                Menu.displayMenu();
                break;
            case INVENTORY:
            case I:
                CheckInventory();
                break;
            case RUN:
            case FLEE:
                run(currentRoom);
                break;
            case SAVE:
                saveGameDataToFile();
                break;
        }

    }

    //swaps weapons
    public static void swap(Rooms currentRoom){
        final String space = "\n";
        final String lines = "************";

        Set<String> keys = Character.getInventory().keySet();
        if(keys.size() == 0){
            System.out.println(Engine.ANSI_RED + "\nYou don't have any weapons in your inventory. Grab some weapons to swap!!" + Engine.ANSI_RESET);
        }
        else {
            System.out.println(space + Engine.ANSI_YELLOW + "Which weapon would you like to hold?\n");
            System.out.println(lines);
            for (String key : keys) {
                System.out.println(key);
            }
            System.out.println(lines + Engine.ANSI_RESET);
            Scanner in = new Scanner(System.in);

            try {
                answer = in.nextLine(); //grabs input
                String newAnswer = capitalizeAll(answer);
                Weapons weapon = Weapons.findWeaponsByName(newAnswer); // input to upper then checks input against ENUMs - implicit

                switch (weapon){
                    case FLAMETHROWER:
                        Character.setCurrentWeapon(Weapons.FLAMETHROWER.getName());
                        System.out.println(Engine.ANSI_YELLOW + "\nYou are now holding a " + newAnswer + "." + Engine.ANSI_RESET);
                        break;
                    case LASER:
                        Character.setCurrentWeapon(Weapons.LASER.getName());
                        System.out.println(Engine.ANSI_YELLOW + "\nYou are now holding a " + newAnswer + "." + Engine.ANSI_RESET);
                        break;
                    case SQUIRT_GUN:
                        Character.setCurrentWeapon(Weapons.SQUIRT_GUN.getName());
                        System.out.println(Engine.ANSI_YELLOW + "\nYou are now holding a " + newAnswer + "." + Engine.ANSI_RESET);
                        break;
                    case TASER_GUN:
                        Character.setCurrentWeapon(Weapons.TASER_GUN.getName());
                        System.out.println(Engine.ANSI_YELLOW + "\nYou are now holding a " + newAnswer + "." + Engine.ANSI_RESET);
                        break;
                    default:
                        System.out.println(Engine.ANSI_RED + "\nYou can't swap with that." + Engine.ANSI_RESET);
                        break;
                }
            } catch (Exception e) {
                System.out.println(Engine.ANSI_RED + "\nYou can't swap with that." + Engine.ANSI_RESET);
            }
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
        Map<String,Boolean> availableItems = getAvailableItems(currentRoom);
        Map<String, Boolean> availableAliens = Alien.getAliens();

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

    /* -- Attack the Alien in the room -- START */
    // Starting Attack the Alien process in the room
    public static void attack(Rooms currentRoom) {   //////  if alien in room
        final String space = "\n";
        Map<String,Boolean> availableItems = getAvailableItems(currentRoom);
        final String lines = "************";
        String a = action.toString();
        System.out.println(space + Engine.ANSI_YELLOW + capitalizeAll(a) + " what?\n");
        System.out.println(lines);

        Set<String> keys = availableItems.keySet();
        for (String key : keys) {
            System.out.println(key);
        }
        System.out.println(lines + Engine.ANSI_RESET);

        Scanner in = new Scanner(System.in);
        String answer = in.nextLine();
        String newAnswer = capitalizeAll(answer);

        if(keys.contains(newAnswer)) {
            try {
                Set<String> aliens = Alien.getAliens().keySet();
                if(aliens.contains(newAnswer)){
                    if(Character.getHealth() == 0 || Oxygen.getOxygen() == 0) {
                        death();
                        System.exit(0); // TODO: make start screen to redirect to game start scene instead of exiting
                    }
                    else {
                        int alienHealthPoints = 0;
                        int alienDamagePoints = 0;
                        switch (newAnswer){
                            case "Vermin":
                                alienHealthPoints = Alien.getT1Hp();
                                alienDamagePoints = Alien.getT1Dmg();
                                break;
                            case "Canine":
                                alienHealthPoints = Alien.getT2Hp();
                                alienDamagePoints = Alien.getT2Dmg();
                                break;
                            case "Humanoid":
                                alienHealthPoints = Alien.getT3Hp();
                                alienDamagePoints = Alien.getT3Dmg();
                                break;
                            case "Superhumanoid":
                                alienHealthPoints = Alien.getT4Hp();
                                alienDamagePoints = Alien.getT4Dmg();
                                break;
                        }
                        System.out.println();

                        boolean hasWeapon = false;
                        for(Weapons weapon : Weapons.values()){
                            if(weapon.getName().equals(Character.getCurrentWeapon())){
                                hasWeapon = true;
                                break;
                            }
                        }

                        if(hasWeapon) {
                            int weaponDamagePoints =  Weapons.findWeaponsByName(Character.getCurrentWeapon()).getDamagePoints();
//                            System.out.println(Engine.ANSI_GREEN + Character.getCurrentWeapon() + Engine.ANSI_BLUE + Engine.ANSI_BLUE + " Deals " + Engine.ANSI_RED + weaponDamagePoints + Engine.ANSI_BLUE +" damage." + Engine.ANSI_RESET);
//                            System.out.println(Engine.ANSI_BLUE + "\nAlien HP: " + Engine.ANSI_GREEN + alienHealthPoints + Engine.ANSI_RESET);

                            alienAttack(currentRoom, newAnswer, alienHealthPoints, alienDamagePoints);
                        }
                        else {
                            System.out.println(Engine.ANSI_RED + "You don't have a weapon equipped to fight with. Bad breath won't do!" + Engine.ANSI_RESET);
                            Menu.displayMenu();
                        }
                    }
                }
                else {
                    System.out.println(Engine.ANSI_RED + "\nYou can't attack that!" + Engine.ANSI_RESET);
                    Menu.displayMenu();
                }
            } catch (Exception e) {
                System.out.println();
            }
        }
        else {
            System.out.println(Engine.ANSI_RED + "\nYou can't attack that!" + Engine.ANSI_RESET);
            Menu.displayMenu();
        }
        // What about if user wants to swap weapon in between of fight???
        //Other stuff for sure!!
    }

    // Attack or Run from Alien in the room to previous room
    public static void alienAttackOrRun(Rooms currentRoom, String alienType, int alienHealthPoints, int alienDamagePoints) {
        final String space = "\n";
        System.out.println(Engine.ANSI_YELLOW + "\nDo you want to ATTACK or RUN????" + Engine.ANSI_RESET);
        Scanner input = new Scanner(System.in);
        boolean repeat = true;
        while (repeat) {
            try {
                String answerInput = input.nextLine();
                action = Actions.valueOf(answerInput.toUpperCase());
                if(action == Actions.ATTACK){
                    repeat = false;
                    alienAttack(currentRoom, alienType, alienHealthPoints, alienDamagePoints);
                }
                else if(action == Actions.RUN){
                    repeat = false;
                    run(currentRoom);
                }
                else{
                    System.out.println("You must enter one of the following actions: ATTACK, RUN");
                    repeat = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("You must enter one of the following actions: ATTACK, RUN");
                repeat = true;
            }
        }
    }

    //Attacking the Alien and Alien will attack back to you
    public static void alienAttack(Rooms currentRoom, String alienType, int alienHealthPoints, int alienDamagePoints){
        System.out.println(Engine.ANSI_RED + "\nAttacking Alien..." + Engine.ANSI_RESET);

        try {
            if(Character.getHealth() != 0) {
                int weaponDamagePoints = Weapons.findWeaponsByName(Character.getCurrentWeapon()).getDamagePoints();
                int alienNewHealthPoints = ((alienHealthPoints - weaponDamagePoints) < 0 ? 0 : (alienHealthPoints - weaponDamagePoints));
                switch (alienType) {
                    case "Vermin":
                        Alien.setT1Hp(alienNewHealthPoints); //set Alien health after attack
                        break;
                    case "Canine":
                        Alien.setT2Hp(alienNewHealthPoints); //set Alien health after attack
                        break;
                    case "Humanoid":
                        Alien.setT3Hp(alienNewHealthPoints); //set Alien health after attack
                        break;
                    case "Superhumanoid":
                        Alien.setT4Hp(alienNewHealthPoints); //set Alien health after attack
                        break;
                }
                System.out.println(Engine.ANSI_RED + "\n-" + weaponDamagePoints + " dmg");
                System.out.println(Engine.ANSI_BLUE + "\nAlien HP: " + Engine.ANSI_GREEN + alienNewHealthPoints + Engine.ANSI_RESET);
                TimeUnit.SECONDS.sleep(2);

                if(alienNewHealthPoints != 0){
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Engine.ANSI_RED + "\nOops!! Alien attacked you back...");
                    int characterFinalHealth = -alienDamagePoints;
                    Character.setHealth(characterFinalHealth);
                    System.out.println("\n-" + alienDamagePoints + " dmg" + Engine.ANSI_RESET);
                    System.out.println(Engine.ANSI_BLUE + "\nYour HP: " + Engine.ANSI_GREEN +Character.getHealth() + Engine.ANSI_RESET);

                    if(Character.getHealth() == 0){
                        death();
                        System.exit(0); // TODO: make start screen to redirect to game start scene instead of exiting
                    }
                    else {
                        alienAttackOrRun(currentRoom, alienType, alienNewHealthPoints, alienDamagePoints);
                    }
                }
                else{
                    //Remove from available items of room
                    Map<String,Boolean> availableItems = getAvailableItems(currentRoom);
                    availableItems.remove(alienType);
                    updateItems(currentRoom, availableItems);
                    Map<String,String> inventory = Character.getInventory();
//                    inventory.put("Code", "reply");
                    Character.setInventory(inventory);

                    System.out.println(Engine.ANSI_RED + "\nWoooohoooo!!! You killed the alien!" + Engine.ANSI_RESET);
                    System.out.println(Engine.ANSI_BLUE + "\nThe alien has dropped something." + Engine.ANSI_RESET);
                    availableItems.put("Code", true);
                    Menu.displayMenu();
                }
            }
            else {
                death();
                System.exit(0); // TODO: make start screen to redirect to game start scene instead of exiting
            }
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /* -- Attack the Alien in the room -- END */

    // Investigate the room
    public static void investigate(Rooms currentRoom){
        Map<String,Boolean> availableItems = getAvailableItems(currentRoom);

        final String space = "\n";
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
                    case "Vermin":
                        System.out.println(Engine.ANSI_BLUE + "\nIt's a Vermin like Creature\n"+ Engine.ANSI_RESET);
                        break;
                    case "Canine":
                        System.out.println(Engine.ANSI_BLUE + "\nIt's a Canine like Creature\n"+ Engine.ANSI_RESET);
                        break;
                    case "Humanoid":
                        System.out.println(Engine.ANSI_BLUE + "\nIt looks like you found your crew mate, they look dismembered and there is a large bloody hole in their chest.\n"+
                                "You can see their insides squirming around, their eyes are black with bloody tears leaking from the corners. They notice you and it let's\n"+
                                "out a horrific bellowing growl. This is not your crew mate anymore ... it's coming to get you!!\n"+ Engine.ANSI_RESET);
                        break;
                    case "Superhumanoid":
                        System.out.println(Engine.ANSI_BLUE + "\nIt's a Super Humanoid Creature\n"+ Engine.ANSI_RESET);
                        break;
                }
            }
        }
        Menu.displayMenu();
    }

    //Open something
    public static void open(Rooms currentRoom) {
        Map<String,Boolean> availableItems = getAvailableItems(currentRoom);

        item1 = capitalizeAll(Input.getItem1());; // Chips
        item2 = capitalizeAll(Input.getItem2()); // Oxygen Tank

        Set<String> items = availableItems.keySet();

        if(items.contains(item2) || items.contains(item1)) {
            try {
                itemToOpen = CanOpen.valueOf(item1.toUpperCase()); // cage
                String upperAnswer = item1.toUpperCase();
                if (itemToOpen.toString().equals(upperAnswer)) { // new answer it cage
                    if(!Character.getInventory().containsKey("Code")){ // make the key code not cage
                        System.out.println(Engine.ANSI_RED + "\nIt's locked" + Engine.ANSI_RESET);
                    }else{
                        System.out.println(Engine.ANSI_YELLOW + "\nNew item added to inventory."+ Engine.ANSI_RESET);
                        Map<String,String> newItems = new HashMap<>();
                        newItems = Character.getInventory();
                        newItems.put("Ignition Switch", "reply");
                        // delete item from room and code from inventory
                        availableItems.remove("Ignition Switch");
                        newItems.remove("Code");
                    }
                } else {
                    System.out.println("here");
                    Menu.displayMenu();
                }
            } catch (IllegalArgumentException e) {
                System.out.println(Engine.ANSI_RED + "\nYou can't open that!" + Engine.ANSI_RESET);
            }
        }else if(Input.getItem1().equals("empty")){
            System.out.println(Engine.ANSI_RED + "\n" + Menu.capitalizeAll(action.toString().toLowerCase()) +
                    " what?" + Engine.ANSI_RESET);
        }
        else {
            System.out.println(Engine.ANSI_RED + "\n" + "That's not in this room." + Engine.ANSI_RESET);
        }
        Menu.displayMenu();
    }

    // Grab the item from the room
    public static void grab(Rooms currentRoom){
        Map<String,Boolean> availableItems = getAvailableItems(currentRoom);

        item1 = capitalizeAll(Input.getItem1());; // Chips
        item2 = capitalizeAll(Input.getItem2()); // Oxygen Tank

        Set<String> items = availableItems.keySet();

        if(items.contains(item2) || items.contains(item1)){
            try {
                xItem = Xitems.valueOf(item1.toUpperCase()); // Enum
                String item1Upper = item1.toUpperCase();
                System.out.println(item1Upper);
                if (xItem.toString().equals(item1Upper)){  // if Enum to string == item1 uppercase
                    System.out.println(Engine.ANSI_RED + "\nYou can't grab that!" + Engine.ANSI_RESET);
                    Menu.displayMenu();
                }
            }
            catch(IllegalArgumentException e){
                System.out.println();
            }

            if(item2.equals("Oxygen Tank")){
                Oxygen.incOxygen(100);
                System.out.println(Engine.ANSI_YELLOW + "\nYou just increased " + oxygen + " levels." +
                        Engine.ANSI_RESET);
                availableItems.remove(item2);
                Menu.displayMenu();
            }

            System.out.println(Engine.ANSI_YELLOW + "\n" + item1 + " added to Inventory." + Engine.ANSI_RESET);
            Map<String,String> newItems = new HashMap<>();
            newItems = Character.getInventory();
            newItems.put(item1, "reply");

            // delete item from room
            availableItems.remove(item1);

        }else if(Input.getItem1().equals("empty")){
            System.out.println(Engine.ANSI_RED + "\n" + Menu.capitalizeAll(action.toString().toLowerCase()) +
                    " what?" + Engine.ANSI_RESET);
        }
        else {
            System.out.println(Engine.ANSI_RED + "\n" + "That's not in this room." + Engine.ANSI_RESET);
        }
        Menu.displayMenu();
    }

    // Eat the item from the room
    public static void eat(Rooms currentRoom){
        Map<String,Boolean> availableItems = getAvailableItems(currentRoom);

        item1 = capitalizeAll(Input.getItem1());; // Chips
        item2 = capitalizeAll(Input.getItem2()); // Oxygen Tank

        Set<String> items = availableItems.keySet();

        if(items.contains(item2) || items.contains(item1)){
            try {
                edible = Edibles.valueOf(item1.toUpperCase());
                int edibleItems = 0;

                for(Edibles edible : Edibles.values()){
                    if(items.contains(edible.getName())){
                        edibleItems++;
                        System.out.println(Engine.ANSI_YELLOW + "\nYou ate " + item1 + ".  HP ++" + Engine.ANSI_RESET);
                        int healthPoints = edible.getHealthPoints();
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
        }else if(Input.getItem1().equals("empty")){
            System.out.println(Engine.ANSI_RED + "\n" + Menu.capitalizeAll(action.toString().toLowerCase()) +
                    " what?" + Engine.ANSI_RESET);
        }
        else {
            System.out.println(Engine.ANSI_RED + "\n" + "That's not in this room." + Engine.ANSI_RESET);
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

    // Get available items of current Room
    public static Map<String,Boolean> getAvailableItems(Rooms currentRoom){
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
        return availableItems;
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

    //Display You are dead when Character has 0 HP
    public static void death(){
        System.out.println("\n" + Engine.ANSI_RED +
                "____    ____  ______    __    __          ___      .______       _______     _______   _______     ___       _______               \n" +
                "\\   \\  /   / /  __  \\  |  |  |  |        /   \\     |   _  \\     |   ____|   |       \\ |   ____|   /   \\     |       \\              \n" +
                " \\   \\/   / |  |  |  | |  |  |  |       /  ^  \\    |  |_)  |    |  |__      |  .--.  ||  |__     /  ^  \\    |  .--.  |             \n" +
                "  \\_    _/  |  |  |  | |  |  |  |      /  /_\\  \\   |      /     |   __|     |  |  |  ||   __|   /  /_\\  \\   |  |  |  |             \n" +
                "    |  |    |  `--'  | |  `--'  |     /  _____  \\  |  |\\  \\----.|  |____    |  '--'  ||  |____ /  _____  \\  |  '--'  | __ __ __ __ \n" +
                "    |__|     \\______/   \\______/     /__/     \\__\\ | _| `._____||_______|   |_______/ |_______/__/     \\__\\ |_______/ (__|__|__|__)\n" +
                "                                                                                                                                   ");
    }

    /* -- Save the Game -- START */
    // Save the Game
    public static void saveGameDataToFile() {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            //add elements to Document
            Element rootElement =
                    doc.createElementNS("", "GameState");
            //append root element to document
            doc.appendChild(rootElement);

            //append child elements to root element
            rootElement.appendChild(getGameElements(doc,"CurrentHealth",String.valueOf(Character.getHealth())));
            rootElement.appendChild(getGameElements(doc,"CurrentOxygen",String.valueOf(Oxygen.getOxygen())));
            rootElement.appendChild(getGameElements(doc,"CurrentWeapon",String.valueOf(Character.getCurrentWeapon())));
            rootElement.appendChild(getGameElements(doc,"CurrentRoom",String.valueOf(Character.getCurrentRoom())));
            rootElement.appendChild(getGameElements(doc,"TempRoom",String.valueOf(Character.getTempRoom())));
            rootElement.appendChild(getGameElements(doc,"PreviousRoom",String.valueOf(Character.getPreviousRoom())));

            //append inventory list to root element
            rootElement.appendChild(getGameData(doc,"Inventory",Character.getInventory().keySet()));

            //append room available item list to root element
            rootElement.appendChild(getGameData(doc,"CapsuleRoom",CapsuleRoom.getAvailableItems().keySet()));
            rootElement.appendChild(getGameData(doc,"AlienRoom",AlienRoom.getAvailableItems().keySet()));
            rootElement.appendChild(getGameData(doc,"Kitchen",Kitchen.getAvailableItems().keySet()));
            rootElement.appendChild(getGameData(doc,"ComputerRoom",SupplyRoom.getAvailableItems().keySet()));
            rootElement.appendChild(getGameData(doc,"ControlRoom",ControlRoom.getAvailableItems().keySet()));

            //for output to file, console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //for pretty print
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //write to console or file
            //StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File(System.getProperty("user.dir") + "\\SaveState.xml"));

            //write data
            //transformer.transform(source, console);
            transformer.transform(source, file);
            System.out.println("We saved the game status!!");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Node getGameData(Document doc, String element, Set<String> items) {
        Element data = doc.createElement(element);
        for(String value : items){
            data.appendChild(getGameElements(doc, "Item", value));
        }
        return data;
    }

    //utility method to create text node
    private static Node getGameElements(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
    /* -- Save the Game -- END */


    /*************** GETTER - SETTER METHODS  ******************/
    private static String getActionQuestion() {
        return actionQuestion;
    }

    private static String getActions() {
        return actions;
    }

    private static String getDirections() {
        return directions;
    }

    private static String getInv(){
        return inv;
    }

    public static void clear() {
        for (int i = 0; i < 25; ++i) System.out.println();
    }

    private static String getSaveGame(){
        return saveGame;
    }
}

