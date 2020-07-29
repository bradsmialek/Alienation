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
import java.lang.reflect.Method;
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
    private static CanOpen itemToOpen;
    private static String answer;
    private static String item1;
    private static String item2;
    private static final String oxygen = "O\u2082"; // O₂
    public static int attackCount = 0;

    /*************** PUBLIC METHODS  ******************/
    // This method used to display Menu to user
    public static void displayMenu() throws Exception {
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
    public static void swap(Rooms currentRoom) throws Exception {
        final String space = "\n";
        final String lines = "************";

        item1 = capitalizeAll(Input.getItem1());; // Chips
        item2 = capitalizeAll(Input.getItem2()); // Oxygen Tank

//        Set<String> keys = Character.getInventory().keySet();  //before

        List<String> keys = Character.getInventory();

        if(keys.size() == 0){
            System.out.println(Engine.ANSI_RED + "\nYou don't have any weapons in your inventory. " +
                    "Grab some weapons to swap!!" + Engine.ANSI_RESET);
            Menu.displayMenu();
        }else if(!Input.getItem1().equals("empty")){
            answer = capitalizeAll(Input.getItem1());
        }
        else {
            System.out.println(space + Engine.ANSI_YELLOW + "Which weapon would you like to hold?\n");
            System.out.println(lines);
            for (String key : keys) {
                System.out.println(key);
            }
            System.out.println(lines + Engine.ANSI_RESET);
            Input.getInput();

            try {
//<<<<<<< HEAD
                answer = capitalizeAll(Input.getActionInput());
//=======
//                answer = in.nextLine(); //grabs input
//                String newAnswer = capitalizeAll(answer);
//                Weapons weapon = Weapons.findWeaponsByName(newAnswer); // input to upper then checks input against ENUMs - implicit
//                if(Character.getInventory().contains(weapon.getName())) {
//                    Character.setCurrentWeapon(weapon.getName());
//                    System.out.println(Engine.ANSI_YELLOW + "\nYou are now holding a " + newAnswer + "." + Engine.ANSI_RESET);
//                } else {
//                    System.out.println(Engine.ANSI_RED + "\nYou can't swap with that." + Engine.ANSI_RESET);
//                }
//>>>>>>> 1e088c83802101dcef03e237eaf09f2b3d9c626d
            } catch (Exception e) {
                System.out.println(Engine.ANSI_RED + "\nYou can't swap with that." + Engine.ANSI_RESET);
            }
        }
        Weapons weapon = Weapons.findWeaponsByName(answer);
        switch (weapon){
            case FLAMETHROWER:
                Character.setCurrentWeapon(Weapons.FLAMETHROWER.getName());
                System.out.println(Engine.ANSI_YELLOW + "\nYou are now holding a " + answer + "." + Engine.ANSI_RESET);
                break;
            case LASER:
                Character.setCurrentWeapon(Weapons.LASER.getName());
                System.out.println(Engine.ANSI_YELLOW + "\nYou are now holding a " + answer + "." + Engine.ANSI_RESET);
                break;
            case SQUIRT_GUN:
                Character.setCurrentWeapon(Weapons.SQUIRT_GUN.getName());
                System.out.println(Engine.ANSI_YELLOW + "\nYou are now holding a " + answer + "." + Engine.ANSI_RESET);
                break;
            case TASER_GUN:
                Character.setCurrentWeapon(Weapons.TASER_GUN.getName());
                System.out.println(Engine.ANSI_YELLOW + "\nYou are now holding a " + answer + "." + Engine.ANSI_RESET);
                break;
            default:
                System.out.println(Engine.ANSI_RED + "\nYou can't swap with that." + Engine.ANSI_RESET);
                break;
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
//<<<<<<< HEAD
    public static void run(Rooms currentRoom) throws Exception {
//        Map<String,Boolean> availableItems = getAvailableItems(currentRoom);
//        Map<String, Boolean> availableAliens = Alien.getAliens();
//
//        Set<String> aliens = availableAliens.keySet();
//        Set<String> keysInRoom = availableItems.keySet();
//=======
//    public static void run(Rooms currentRoom) {
        Set<String> aliens = Alien.getAliens().keySet();
        List<String> keysInRoom = Engine.getAvailableItemsMap().get(currentRoom);
//>>>>>>> 1e088c83802101dcef03e237eaf09f2b3d9c626d

        boolean reply = false;
        for (String key : keysInRoom) {
            for(String alien : aliens){
                if(key.equals(alien)){
                    System.out.println(Engine.ANSI_RED + "\n\nYou ran away as fast as you can!" + Engine.ANSI_RESET);
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
//<<<<<<< HEAD
    public static void attack(Rooms currentRoom) throws Exception {
//        Map<String,Boolean> availableItems = getAvailableItems(currentRoom);
        List<String> availableItems = Engine.getAvailableItemsMap().get(currentRoom);

        item1 = capitalizeAll(Input.getItem1());
        item2 = capitalizeAll(Input.getItem2());
//=======
//    public static void attack(Rooms currentRoom) {   //////  if alien in room
//        final String space = "\n";
//        final String lines = "************";
//        String a = action.toString();
//        System.out.println(space + Engine.ANSI_YELLOW + capitalizeAll(a) + " what?\n");
//        System.out.println(lines);

//        List<String> keys = Engine.getAvailableItemsMap().get(currentRoom);
//        for (String key : keys) {
//            System.out.println(key);
//        }
//        System.out.println(lines + Engine.ANSI_RESET);
//>>>>>>> 1e088c83802101dcef03e237eaf09f2b3d9c626d

        Set<String> items = availableItems.keySet();
        Set<String> aliens = Alien.getAliens().keySet();

        if(items.contains(item2) || items.contains(item1)) {
            try {
                if(aliens.contains(item1)){
                    if(Character.getHealth() == 0 || Oxygen.getOxygen() == 0) {
//<<<<<<< HEAD
//                        System.out.println(Death.death());
//                        System.exit(0); // TODO: make start screen to redirect to game start scene instead of exiting
//                    }
//                    else {
//                        int alienHealthPoints = 0;
//                        int alienDamagePoints = 0;
//                        switch (item1){
//                            case "Vermin":
//                                alienHealthPoints = Alien.getT1Hp();
//                                alienDamagePoints = Alien.getT1Dmg();
//                                break;
//                            case "Canine":
//                                alienHealthPoints = Alien.getT2Hp();
//                                alienDamagePoints = Alien.getT2Dmg();
//                                break;
//                            case "Humanoid":
//                                alienHealthPoints = Alien.getT3Hp();
//                                alienDamagePoints = Alien.getT3Dmg();
//                                break;
//                            case "Superhumanoid":
//                                alienHealthPoints = Alien.getT4Hp();
//                                alienDamagePoints = Alien.getT4Dmg();
//                                break;
//                        }
//=======
                        Death.death();
                    }
                    else {
//>>>>>>> 1e088c83802101dcef03e237eaf09f2b3d9c626d
                        System.out.println();
                        boolean hasWeapon = false;
                        for(Weapons weapon : Weapons.values()){
                            if(weapon.getName().equals(Character.getCurrentWeapon())){
                                hasWeapon = true;
                                break;
                            }
                        }

                        if(hasWeapon) {
//<<<<<<< HEAD
//                            alienAttack(currentRoom, item1, alienHealthPoints, alienDamagePoints);
//=======
                            alienAttack(currentRoom, newAnswer);
//>>>>>>> 1e088c83802101dcef03e237eaf09f2b3d9c626d
                        }
                        else {
                            System.out.println(Engine.ANSI_RED + "You don't have a weapon equipped to fight with. " +
                                    "Bad breath won't do!" + Engine.ANSI_RESET);
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
        }else if(Input.getItem1().equals("empty")){
            System.out.println(Engine.ANSI_RED + "\n" + Menu.capitalizeAll(action.toString().toLowerCase()) +
                    " what?" + Engine.ANSI_RESET);
        }
        else {
        System.out.println(Engine.ANSI_RED + "\n" + "That's not in this room." + Engine.ANSI_RESET);
        }
        Menu.displayMenu();
    }

    // Attack or Run from Alien in the room to previous room
<<<<<<< HEAD
    public static void alienAttackOrRun(Rooms currentRoom, String alienType, int alienHealthPoints, int alienDamagePoints) {
        System.out.println(Engine.ANSI_YELLOW + "\nWhat do you want to do?" + Engine.ANSI_RESET);

        boolean repeat = true;
        while (repeat) {
            try {
                Input.getInput();
                String input = Input.getActionInput();
                action = Actions.valueOf(input.toUpperCase());

                switch (action){
                    case FIGHT:
                    case ATTACK:
                        repeat = false;
                        alienAttack(currentRoom, alienType, alienHealthPoints, alienDamagePoints);
                        break;
                    case RUN:
                    case FLEE:
                        repeat = false;
                        run(currentRoom);
                        break;
                    case EAT:
                        repeat = false;
                        eat(currentRoom);
                    case SWAP:
                    case EQUIP:
                        repeat = false;
                        swap(currentRoom);
                        break;
                    default:
                        System.out.println("You must enter one of the following actions: ATTACK, RUN");
                        repeat = true;
                        break;

=======
    public static void alienAttackOrRun(Rooms currentRoom, String alienType) {
        final String space = "\n";
        System.out.println(Engine.ANSI_YELLOW + "\nDo you want to ATTACK or RUN????" + Engine.ANSI_RESET);
        Scanner input = new Scanner(System.in);
        boolean repeat = true;
        while (repeat) {
            try {
                String answerInput = input.nextLine(); //grabs input
                action = Actions.valueOf(answerInput.toUpperCase()); // input to upper then checks input against ENUMs - implicit
                if(action == Actions.ATTACK){
                    repeat = false;
                    alienAttack(currentRoom, alienType);
                }
                else if(action == Actions.RUN){
                    repeat = false;
                    run(currentRoom);
                }
                else{
                    System.out.println("You must enter one of the following actions: ATTACK, RUN");
                    repeat = true;
>>>>>>> 1e088c83802101dcef03e237eaf09f2b3d9c626d
                }
            } catch (IllegalArgumentException e) {
                System.out.println(Engine.ANSI_RED + "\nCan't do that!" + Engine.ANSI_RESET);
                repeat = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Attacking the Alien and Alien will attack back to you
    public static void alienAttack(Rooms currentRoom, String alienType){
        System.out.println(Engine.ANSI_RED + "\nAttacking Alien..." + Engine.ANSI_RESET);
        attackCount ++;
        try {
            if(Character.getHealth() != 0) {
                int alienHealthPoints = Alien.getPoints(alienType,"HP");
                int alienDamagePoints = Alien.getPoints(alienType,"DP");
                int weaponDamagePoints = Weapons.findWeaponsByName(Character.getCurrentWeapon()).getDamagePoints();
                int alienNewHealthPoints = ((alienHealthPoints - weaponDamagePoints) < 0 ? 0 : (alienHealthPoints - weaponDamagePoints));
                Alien.setPoints(alienType,"HP", alienNewHealthPoints);

                System.out.println(Engine.ANSI_RED + "\n-" + weaponDamagePoints + " dmg");
                System.out.println(Engine.ANSI_BLUE + "\nAlien HP: " + Engine.ANSI_GREEN + alienNewHealthPoints +
                        Engine.ANSI_RESET);
                TimeUnit.SECONDS.sleep(2);

                if(alienNewHealthPoints != 0){
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Engine.ANSI_RED + "\nOops!! Alien attacked you back...");
                    int characterFinalHealth = -alienDamagePoints;
                    Character.setHealth(characterFinalHealth);
                    System.out.println("\n-" + alienDamagePoints + " dmg" + Engine.ANSI_RESET);
                    System.out.println(Engine.ANSI_BLUE + "\nYour HP: " + Engine.ANSI_GREEN +Character.getHealth() +
                            Engine.ANSI_RESET);

                    if(Character.getHealth() == 0){
<<<<<<< HEAD
                        System.out.println(Death.death());
                        System.exit(0);
=======
                        death();
>>>>>>> 1e088c83802101dcef03e237eaf09f2b3d9c626d
                    }
                    else {
                        alienAttackOrRun(currentRoom, alienType);
                    }
                }
                else{
                    //Remove from available items of room
                    List<String> availableItems = Engine.getAvailableItemsMap().get(currentRoom);
                    availableItems.remove(alienType);
<<<<<<< HEAD
                    updateItems(currentRoom, availableItems);
                    Map<String,String> inventory = Character.getInventory();
                    Character.setInventory(inventory);
                    System.out.println(Engine.ANSI_RED + "\nThe alien is fatally wounded and falls to it's death " +
                            "in a pool of blood." + Engine.ANSI_RESET);
=======
                    System.out.println(Engine.ANSI_RED + "\nWoooohoooo!!! You killed the alien!" + Engine.ANSI_RESET);
>>>>>>> 1e088c83802101dcef03e237eaf09f2b3d9c626d
                    System.out.println(Engine.ANSI_BLUE + "\nThe alien has dropped something." + Engine.ANSI_RESET);
                    List<String> inventory = Character.getInventory();
                    inventory.add("Code");
                    Character.setInventory(inventory);
                    Engine.setAvailableItemsMap(currentRoom, availableItems);
                    Menu.displayMenu();
                }
            }
            else {
<<<<<<< HEAD
                System.out.println(Death.death());
                System.exit(0);
=======
                death();
>>>>>>> 1e088c83802101dcef03e237eaf09f2b3d9c626d
            }
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Investigate the room
<<<<<<< HEAD
    public static void investigate(Rooms currentRoom) throws Exception {
        Map<String,Boolean> availableItems = getAvailableItems(currentRoom);

=======
    public static void investigate(Rooms currentRoom){
>>>>>>> 1e088c83802101dcef03e237eaf09f2b3d9c626d
        final String space = "\n";
        final String lines = "************";
        System.out.println(space + Engine.ANSI_YELLOW + "You see:\n");
        System.out.println(lines);

        List<String> keys = Engine.getAvailableItemsMap().get(currentRoom);
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
<<<<<<< HEAD
    public static void open(Rooms currentRoom) throws Exception {
        Map<String,Boolean> availableItems = getAvailableItems(currentRoom);

        item1 = capitalizeAll(Input.getItem1());; // Chips
        item2 = capitalizeAll(Input.getItem2()); // Oxygen Tank

        Set<String> items = availableItems.keySet();
=======
    public static void open(Rooms currentRoom) {
        final String space = "\n";
        final String lines = "************";
        System.out.println(space + Engine.ANSI_YELLOW + "Open what?\n");
        System.out.println(lines);

        List<String> items = Engine.getAvailableItemsMap().get(currentRoom);
        for (String item : items) {
            System.out.println(item);
        }
        System.out.println(lines + Engine.ANSI_RESET);

        Scanner in = new Scanner(System.in);
        String answer = in.nextLine(); // cage
        String newAnswer = capitalizeAll(answer);
>>>>>>> 1e088c83802101dcef03e237eaf09f2b3d9c626d

        if(items.contains(item2) || items.contains(item1)) {
            try {
                itemToOpen = CanOpen.valueOf(item1.toUpperCase()); // cage
                String upperAnswer = item1.toUpperCase();
                if (itemToOpen.toString().equals(upperAnswer)) { // new answer it cage
                    if(!Character.getInventory().contains("Code")){ // make the key code not cage
                        System.out.println(Engine.ANSI_RED + "\nIt's locked" + Engine.ANSI_RESET);
                    }else{
                        System.out.println(Engine.ANSI_YELLOW + "\nNew item added to inventory."+ Engine.ANSI_RESET);
                        List<String> newItems = new ArrayList<>();
                        newItems = Character.getInventory();
                        newItems.add("Ignition Switch");
                        // delete item from room and code from inventory
                        items.remove("Ignition Switch");
                        Engine.setAvailableItemsMap(currentRoom, items);
                        newItems.remove("Code");
                        Character.setInventory(newItems);
                    }
                } else {
                    System.out.println("here");
                    Menu.displayMenu();
                }
            } catch (IllegalArgumentException e) {
                System.out.println(Engine.ANSI_RED + "\nYou can't open that!" + Engine.ANSI_RESET);
            } catch (Exception e) {
                e.printStackTrace();
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
<<<<<<< HEAD
    public static void grab(Rooms currentRoom) throws Exception {
        Map<String,Boolean> availableItems = getAvailableItems(currentRoom);

        item1 = capitalizeAll(Input.getItem1());
        item2 = capitalizeAll(Input.getItem2());
        Set<String> items = availableItems.keySet();
=======
    public static void grab(Rooms currentRoom){
        final String space = "\n";
        final String lines = "************";
        System.out.println(space + Engine.ANSI_YELLOW + action + " what?\n");
        System.out.println(lines);

        List<String> items = Engine.getAvailableItemsMap().get(currentRoom);
        for (String item : items) {
            System.out.println(item);
        }
        System.out.println(lines + Engine.ANSI_RESET);

        Scanner in = new Scanner(System.in);
        String answer = in.nextLine();
        String newAnswer = capitalizeAll(answer);
>>>>>>> 1e088c83802101dcef03e237eaf09f2b3d9c626d

        if(items.contains(item2) || items.contains(item1)){
            try {
<<<<<<< HEAD
                xItem = Xitems.valueOf(item1.toUpperCase()); // Enum
                String item1Upper = item1.toUpperCase();
                if (xItem.toString().equals(item1Upper)){  // if Enum to string == item1 uppercase
=======
                if (getXItems().contains(newAnswer)){
>>>>>>> 1e088c83802101dcef03e237eaf09f2b3d9c626d
                    System.out.println(Engine.ANSI_RED + "\nYou can't grab that!" + Engine.ANSI_RESET);
                    Menu.displayMenu();
                }
            }
            catch(IllegalArgumentException e){
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(item2.equals("Oxygen Tank")){
                Oxygen.incOxygen(100);
<<<<<<< HEAD
                System.out.println(Engine.ANSI_YELLOW + "\nYou just increased " + oxygen + " levels." +
                        Engine.ANSI_RESET);
                availableItems.remove(item2);
                Menu.displayMenu();
            }

            System.out.println(Engine.ANSI_YELLOW + "\n" + item1 + " added to Inventory." + Engine.ANSI_RESET);
            Map<String,String> newItems;
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
=======
                System.out.println(Engine.ANSI_YELLOW + "\nYou just increased " + oxygen + " levels." + Engine.ANSI_RESET);
                items.remove(newAnswer);
                Engine.setAvailableItemsMap(currentRoom, items);
                Menu.displayMenu();
            }

            System.out.println(Engine.ANSI_YELLOW + "\n" + newAnswer + " added to Inventory." + Engine.ANSI_RESET);
            List<String> newItems = new ArrayList<>();
            newItems = Character.getInventory();
            newItems.add(newAnswer);
            Character.setInventory(newItems);

            // delete item from room
            items.remove(newAnswer);
            Engine.setAvailableItemsMap(currentRoom, items);
        }else{
            System.out.println(Engine.ANSI_RED + "\nYou can't grab that!" + Engine.ANSI_RESET);
>>>>>>> 1e088c83802101dcef03e237eaf09f2b3d9c626d
        }
        Menu.displayMenu();
    }

    //Get items which user can't grab
    private static List<String> getXItems(){
        List<String> xItems = new ArrayList<>();
        xItems.addAll(Arrays.asList("Locker", "Pods", "Cabinets", "Computer", "Desk", "Sofa",
                "Racks", "Cage", "Supplies", "Refrigerator", "Microwave", "Dustbin",
                "Monitor", "Control Panel", "Pilot Seats", "Humanoid", "Bed", "Mirror", "Old Box"));
        return xItems;
    }

    // Eat the item from the room
<<<<<<< HEAD
    public static void eat(Rooms currentRoom) throws Exception {
        Map<String,Boolean> availableItems = getAvailableItems(currentRoom);

        item1 = capitalizeAll(Input.getItem1());; // Chips
        item2 = capitalizeAll(Input.getItem2()); // Oxygen Tank
=======
    public static void eat(Rooms currentRoom){
        final String space = "\n";
        final String lines = "************";
        boolean repeat = true;

        System.out.println(space + Engine.ANSI_YELLOW + "Eat what?\n");
        System.out.println(lines);
        List<String> items = Engine.getAvailableItemsMap().get(currentRoom);
        for (String item : items) {
            System.out.println(item);
        }
        System.out.println(lines + Engine.ANSI_RESET);
        Scanner in = new Scanner(System.in);

        //TODO: OPTIONS FOR INVENTORY OR ROOM
        try {
            answer = in.nextLine(); //grabs input
            edible = Edibles.valueOf(answer.toUpperCase()); // input to upper then checks input against ENUMs - implicit

            int edibleItems = 0;
            for(Edibles edible : Edibles.values()){
                if(items.contains(edible.getName())){
                    edibleItems++;
                    System.out.println(Engine.ANSI_YELLOW + "\nYou ate " + answer + ".  HP ++" + Engine.ANSI_RESET);
                    int healthPoints = ((Edibles)edible).getHealthPoints();
                    //Increase health points
                    Character.setHealth(healthPoints);
                    //Remove from available items of room
                    Engine.getAvailableItemsMap().get(currentRoom).remove(edible.getName());
>>>>>>> 1e088c83802101dcef03e237eaf09f2b3d9c626d

        Set<String> items = availableItems.keySet();

        if(items.contains(item2) || items.contains(item1) || Character.getInventory().containsKey(item1)){
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
                    }else if(Character.getInventory().containsKey(item1)){
                        edibleItems++;
                        System.out.println(Engine.ANSI_YELLOW + "\nYou ate " + item1 + ".  HP ++" + Engine.ANSI_RESET);
                        int healthPoints = edible.getHealthPoints();
                        //Increase health points
                        Character.setHealth(healthPoints);
                        Map<String,String> newItems;
                        newItems = Character.getInventory();
                        newItems.remove(item1);
                    }
                }
                if(edibleItems == 0){
                    System.out.println(Engine.ANSI_RED + "There is nothing to eat!!" + Engine.ANSI_RESET);
                }
                updateItems(currentRoom, availableItems);
            } catch (IllegalArgumentException e) {
                System.out.println(Engine.ANSI_RED + "\nYou can't eat that." + Engine.ANSI_RESET);
            }
<<<<<<< HEAD
        }else if(Input.getItem1().equals("empty")){
            System.out.println(Engine.ANSI_RED + "\n" + Menu.capitalizeAll(action.toString().toLowerCase()) +
                    " what?" + Engine.ANSI_RESET);
        }
        else {
            System.out.println(Engine.ANSI_RED + "\n" + "That's not in this room or your inventory." + Engine.ANSI_RESET);
=======
            if(edibleItems == 0){
                System.out.println(Engine.ANSI_RED + "There is nothing to eat!!" + Engine.ANSI_RESET);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(Engine.ANSI_RED + "\nYou can't eat that." + Engine.ANSI_RESET);
>>>>>>> 1e088c83802101dcef03e237eaf09f2b3d9c626d
        }
        Menu.displayMenu();
    }

    // Move Room from one to another
<<<<<<< HEAD
    public static void moveRoom(String direction, Rooms currentRoom) throws Exception {
        Rooms nextRoom = getRoom(direction, currentRoom);

=======
    public static void moveRoom(String direction, Rooms currentRoom){
        Rooms nextRoom = Engine.getAvailableDirectionsMap().get(currentRoom).get(direction);
>>>>>>> 1e088c83802101dcef03e237eaf09f2b3d9c626d
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

    // Load the next room
    public static void loadRoom(Rooms newRoom) throws Exception {
        Character.setCurrentRoom(newRoom);
        Room r = new CapsuleRoom();
        switch (newRoom){
            case AlienRoom:
                r = new AlienRoom();
                break;
            case Kitchen:
                r = new Kitchen();
                break;
            case SupplyRoom:
                r = new SupplyRoom();
                break;
            case ControlRoom:
                r = new ControlRoom();
                break;
        }
        r.loadEnvironment();
    }

    // Get available items of a room
    public static void CheckInventory() throws Exception {
        final String space = "\n";
        List<String> inventory = Character.getInventory();

        final String lines = "************";
        System.out.println(space + Engine.ANSI_YELLOW + "Inventory\n");
        System.out.println(lines);
        for (String item : inventory) {
            System.out.println(item);
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

//<<<<<<< HEAD
//=======
//    //Display You are dead when Character has 0 HP
//    public static void death(){
//        System.out.println("\n" + Engine.ANSI_RED +
//                "____    ____  ______    __    __          ___      .______       _______     _______   _______     ___       _______               \n" +
//                "\\   \\  /   / /  __  \\  |  |  |  |        /   \\     |   _  \\     |   ____|   |       \\ |   ____|   /   \\     |       \\              \n" +
//                " \\   \\/   / |  |  |  | |  |  |  |       /  ^  \\    |  |_)  |    |  |__      |  .--.  ||  |__     /  ^  \\    |  .--.  |             \n" +
//                "  \\_    _/  |  |  |  | |  |  |  |      /  /_\\  \\   |      /     |   __|     |  |  |  ||   __|   /  /_\\  \\   |  |  |  |             \n" +
//                "    |  |    |  `--'  | |  `--'  |     /  _____  \\  |  |\\  \\----.|  |____    |  '--'  ||  |____ /  _____  \\  |  '--'  | __ __ __ __ \n" +
//                "    |__|     \\______/   \\______/     /__/     \\__\\ | _| `._____||_______|   |_______/ |_______/__/     \\__\\ |_______/ (__|__|__|__)\n" +
//                "                                                                                                                                   " + Engine.ANSI_RESET);
//
//        StartNewOrQuitGame();
//    }

    //Display when You won
    //TODO: replace ASCII for "You Won"
//    public static void win(){
//        System.out.println("\n" + Engine.ANSI_RED +
//                "____    ____  ______    __    __          ___      .______       _______     _______   _______     ___       _______               \n" +
//                "\\   \\  /   / /  __  \\  |  |  |  |        /   \\     |   _  \\     |   ____|   |       \\ |   ____|   /   \\     |       \\              \n" +
//                " \\   \\/   / |  |  |  | |  |  |  |       /  ^  \\    |  |_)  |    |  |__      |  .--.  ||  |__     /  ^  \\    |  .--.  |             \n" +
//                "  \\_    _/  |  |  |  | |  |  |  |      /  /_\\  \\   |      /     |   __|     |  |  |  ||   __|   /  /_\\  \\   |  |  |  |             \n" +
//                "    |  |    |  `--'  | |  `--'  |     /  _____  \\  |  |\\  \\----.|  |____    |  '--'  ||  |____ /  _____  \\  |  '--'  | __ __ __ __ \n" +
//                "    |__|     \\______/   \\______/     /__/     \\__\\ | _| `._____||_______|   |_______/ |_______/__/     \\__\\ |_______/ (__|__|__|__)\n" +
//                "                                                                                                                                   " + Engine.ANSI_RESET);
//
//        StartNewOrQuitGame();
//    }

    private static void StartNewOrQuitGame(){
        File gameState = new File(System.getProperty("user.dir") + "\\SaveState.xml");
        if (gameState.exists()) {
            gameState.delete();
        }

        final String lines = "---------------------------------------------------------------------------------------------------------------------------------";
        System.out.println("\nDo you want to Start New Game?? Yes<Y> or No<N>");
        System.out.println(lines);

        boolean repeat = true;
        Scanner in = new Scanner(System.in);
        while (repeat) {
            try {
                String answer = in.nextLine(); //grabs input
                if (answer.toUpperCase().equals("Y")) {
                    Engine.ResumeOrNewGame(true);
                    repeat = false;
                } else if (answer.toUpperCase().equals("N")) {
                    System.exit(0);
                } else {
                    System.out.println("You must enter one of the following actions: Y, N");
                    repeat = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("You must enter one of the following actions: Y, N");
                repeat = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//>>>>>>> 1e088c83802101dcef03e237eaf09f2b3d9c626d
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
            rootElement.appendChild(getGameData(doc,"Inventory",Character.getInventory()));

            //append room available item list to root element
            for (Rooms room : Rooms.values()) {
                rootElement.appendChild(getGameData(doc,room.toString(),Engine.getAvailableItemsMap().get(room)));
            }

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

    private static Node getGameData(Document doc, String element, List<String> items) {
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
