package com.alienation.game;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

/**
 * Game Engine
 * This class is used to start the Engine of the game
 */
public class Engine {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    private static Map<Rooms,List<String>> availableItemsMap = new HashMap<>();
    private static Map<Rooms,Map<String, Rooms>> availableDirectionsMap = new HashMap<>();

    /*************** PUBLIC METHODS  ******************/
    // Method when our Game Engine starts
    public static void start() throws Exception {
        ResumeOrNewGame(false);
    }

    //User wants to resume old game or start new one
    public static void ResumeOrNewGame(Boolean isDead) throws Exception {
        if(!isDead) {
            File gameState = new File(System.getProperty("user.dir") + "\\SaveState.xml");
            if (gameState.exists()) {
                final String lines = "---------------------------------------------------------------------------------------------------------------------------------";
                System.out.println("\nDo you want to Resume Game<R> or Play New Game<N>??");
                System.out.println(lines);

                boolean repeat = true;
                boolean isNew = true;
                Scanner in = new Scanner(System.in);
                while (repeat) {
                    try {
                        String answer = in.nextLine(); //grabs input
                        if (answer.toUpperCase().equals("N")) {
                            gameState.delete();
                            repeat = false;
                        } else if (answer.toUpperCase().equals("R")) {
                            isNew = false;
                            repeat = false;
                        } else {
                            System.out.println("You must enter one of the following actions: R, N");
                            repeat = true;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("You must enter one of the following actions: R, N");
                        repeat = true;
                    }
                }
                if (!isNew) {
                    DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
                    try {
                        DocumentBuilder builder = domFactory.newDocumentBuilder();
                        Document dDoc = builder.parse(gameState);
                        dDoc.getDocumentElement().normalize();

                        // Get Character Health
                        int currentHealth = Integer.parseInt(dDoc.getElementsByTagName("CurrentHealth").item(0).getTextContent());
                        Character.setHealth(currentHealth - Character.getHealth());

                        //Get Character Oxygen level
                        int currentOxygen = Integer.parseInt(dDoc.getElementsByTagName("CurrentOxygen").item(0).getTextContent()) + 10;
                        Oxygen.setOxygen(currentOxygen);

                        //Get Current Weapon
                        String currentWeapon = dDoc.getElementsByTagName("CurrentWeapon").item(0).getTextContent();
                        Character.setCurrentWeapon(currentWeapon);

                        //Get Temp Room
                        String tempRoom = dDoc.getElementsByTagName("TempRoom").item(0).getTextContent();
                        Character.setTempRoom(Rooms.valueOf(tempRoom));

                        //Get Previous Room
                        String previousRoom = dDoc.getElementsByTagName("PreviousRoom").item(0).getTextContent();
                        Character.setPreviousRoom(Rooms.valueOf(previousRoom));

                        //Get inventory
                        List<String> inventory = new ArrayList<>();
                        NodeList inventoryNodeList = dDoc.getElementsByTagName("Inventory");
                        Element eElement = (Element) inventoryNodeList.item(0);
                        NodeList childItems = eElement.getElementsByTagName("Item");
                        for (int temp = 0; temp < childItems.getLength(); temp++) {
                            inventory.add(childItems.item(temp).getTextContent());
                        }
                        Character.setInventory(inventory);

                        //Get Available Items Map
                        availableItemsMap.clear();
                        for(Rooms room : Rooms.values()){
                            NodeList capsuleRoomNodeList = dDoc.getElementsByTagName(room.toString());
                            List<String> availableItems = getAvailableItemsXML(capsuleRoomNodeList);
                            availableItemsMap.put(room,availableItems);
                        }

                        //Get Current Room
                        String currentRoom = dDoc.getElementsByTagName("CurrentRoom").item(0).getTextContent();
                        Menu.loadRoom(Rooms.valueOf(currentRoom));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    new CapsuleRoom().loadEnvironment();
                }
            } else {
                new CapsuleRoom().loadEnvironment();
            }
        } else {
            ResetData();
            new CapsuleRoom().loadEnvironment();
        }
    }

    //Reset all data if user restarts the game
    private static void ResetData(){
        Character.setHealth(5);
        Character.setCurrentRoom(Rooms.CapsuleRoom);
        Character.setInventory(new ArrayList<String>());
        Character.setPreviousRoom(null);
        Character.setCurrentWeapon("Bad Breath");
        Character.setTempRoom(null);
        Oxygen.setOxygen(50);
        new CapsuleRoom().count = 0;
        //TODO store attackCount instead
        availableItemsMap.clear();
    }

    //Get available items for room from XML
    private static List<String> getAvailableItemsXML(NodeList nList){
        List<String> availableItems = new ArrayList<>();
        Element eElement = (Element) nList.item(0);
        NodeList childItems = eElement.getElementsByTagName("Item");
        for (int temp = 0; temp < childItems.getLength(); temp++) {
            availableItems.add(childItems.item(temp).getTextContent());
        }
        return availableItems;
    }

    // Get Available Items Map
    public static Map<Rooms, List<String>> getAvailableItemsMap() {
        if(availableItemsMap.size() == 0) {
            availableItemsMap.put(Rooms.CapsuleRoom, new ArrayList<String>(Arrays.asList("Pods", "Oxygen Tank", "Racks", "Lockers")));
            availableItemsMap.put(Rooms.AlienRoom, new ArrayList<String>(Arrays.asList("Humanoid", "Bed", "Mirror", "Old Box")));
            availableItemsMap.put(Rooms.Kitchen, new ArrayList<String>(Arrays.asList("Refrigerator", "Microwave", "Cabinets", "Dustbin", "Snickers", "Flamethrower")));
            availableItemsMap.put(Rooms.SupplyRoom, new ArrayList<String>(Arrays.asList("Computer", "Desk", "Sofa", "Racks", "Supplies", "Cage")));
            availableItemsMap.put(Rooms.ControlRoom, new ArrayList<String>(Arrays.asList("Monitor", "Control Panel", "Pilot Seats", "Laser", "Chips")));
        }
        return availableItemsMap;
    }

    //set availableI items map
    public static void setAvailableItemsMap(Rooms key, List<String> items){
        availableItemsMap.remove(key);
        availableItemsMap.put(key, items);
    }

    //Get Available Directions Map
    public static Map<Rooms,Map<String, Rooms>> getAvailableDirectionsMap() {
        Map<String, Rooms> roomsMap = new HashMap<String, Rooms>();
        roomsMap.put("N",Rooms.SupplyRoom);
        roomsMap.put("S",Rooms.ControlRoom);
        roomsMap.put("E",Rooms.AlienRoom);
        roomsMap.put("W",null);
        availableDirectionsMap.put(Rooms.CapsuleRoom, roomsMap);

        roomsMap = new HashMap<String, Rooms>();
        roomsMap.put("N",Rooms.Kitchen);
        roomsMap.put("S",null);
        roomsMap.put("E",null);
        roomsMap.put("W",Rooms.CapsuleRoom);
        availableDirectionsMap.put(Rooms.AlienRoom, roomsMap);

        roomsMap = new HashMap<String, Rooms>();
        roomsMap.put("N",null);
        roomsMap.put("S", Rooms.AlienRoom);
        roomsMap.put("E",null);
        roomsMap.put("W", Rooms.SupplyRoom);
        availableDirectionsMap.put(Rooms.Kitchen, roomsMap);

        roomsMap = new HashMap<String, Rooms>();
        roomsMap.put("N",null);
        roomsMap.put("S",Rooms.CapsuleRoom);
        roomsMap.put("E",Rooms.Kitchen);
        roomsMap.put("W",null);
        availableDirectionsMap.put(Rooms.SupplyRoom, roomsMap);

        roomsMap = new HashMap<String, Rooms>();
        roomsMap.put("N", Rooms.CapsuleRoom);
        roomsMap.put("S",null);
        roomsMap.put("E",null);
        roomsMap.put("W",null);
        availableDirectionsMap.put(Rooms.ControlRoom, roomsMap);

        return availableDirectionsMap;
    }
}
