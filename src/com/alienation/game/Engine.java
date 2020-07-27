package com.alienation.game;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

    /*************** PUBLIC METHODS  ******************/
    public static void start(){
        System.out.println(Banner.getBanner());
        ResumeOrNewGame();
    }

    private static void ResumeOrNewGame(){
        File gameState = new File(System.getProperty("user.dir") + "\\SaveState.xml");
        if(gameState.exists()) {
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
            if(!isNew){
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
                    if(currentOxygen > Oxygen.getOxygen()) {
                        Oxygen.incOxygen(currentOxygen - Oxygen.getOxygen());
                    } else {
                        Oxygen.minOxygen(Oxygen.getOxygen() - currentOxygen);
                    }

                    //Get Current Weapon
                    String currentWeapon = dDoc.getElementsByTagName("CurrentWeapon").item(0).getTextContent();
                    Character.setCurrentWeapon(currentWeapon);

                    //Get Temp Room
                    String tempRoom = dDoc.getElementsByTagName("TempRoom").item(0).getTextContent();
                    Character.setTempRoom(Rooms.valueOf(tempRoom));

                    //Get Previous Room
                    String previousRoom = dDoc.getElementsByTagName("PreviousRoom").item(0).getTextContent();
                    Character.setPreviousRoom(Rooms.valueOf(previousRoom));

                    Map<String, String> inventory = new HashMap<>();
                    NodeList inventoryNodeList = dDoc.getElementsByTagName("Inventory");
                    Element eElement = (Element) inventoryNodeList.item(0);
                    NodeList childItems = eElement.getElementsByTagName("Item");
                    for (int temp = 0; temp < childItems.getLength(); temp++) {
                        inventory.put(childItems.item(temp).getTextContent(), "reply");
                    }
                    Character.setInventory(inventory);

                    Map<String,Boolean> availableItems = new HashMap<String, Boolean>();
                    //Get Capsule Room available Items
                    NodeList capsuleRoomNodeList = dDoc.getElementsByTagName("CapsuleRoom");
                    availableItems = getAvailableItemsXML(capsuleRoomNodeList);
                    CapsuleRoom.setAvailableItems(availableItems);

                    //Get Alien Room available Items
                    NodeList alienRoomNodeList = dDoc.getElementsByTagName("AlienRoom");
                    availableItems = getAvailableItemsXML(alienRoomNodeList);
                    AlienRoom.setAvailableItems(availableItems);

                    //Get Kitchen available Items
                    NodeList kitchenNodeList = dDoc.getElementsByTagName("Kitchen");
                    availableItems = getAvailableItemsXML(kitchenNodeList);
                    Kitchen.setAvailableItems(availableItems);

                    //Get Computer Room available Items
                    NodeList computerRoomNodeList = dDoc.getElementsByTagName("ComputerRoom");
                    availableItems = getAvailableItemsXML(computerRoomNodeList);
                    SupplyRoom.setAvailableItems(availableItems);

                    //Get Control Room available Items
                    NodeList controlRoomNodeList = dDoc.getElementsByTagName("ControlRoom");
                    availableItems = getAvailableItemsXML(controlRoomNodeList);
                    ControlRoom.setAvailableItems(availableItems);

                    //Get Current Room
                    String currentRoom = dDoc.getElementsByTagName("CurrentRoom").item(0).getTextContent();
                    Menu.loadRoom(Rooms.valueOf(currentRoom));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                CapsuleRoom.loadEnvironment();
            }
        } else {
            CapsuleRoom.loadEnvironment();
        }
    }

    //Get available items for room from XML
    private static Map<String, Boolean> getAvailableItemsXML(NodeList nList){
        Map<String,Boolean> availableItems = new HashMap<String, Boolean>();
        Element eElement = (Element) nList.item(0);
        NodeList childItems = eElement.getElementsByTagName("Item");
        for (int temp = 0; temp < childItems.getLength(); temp++) {
            availableItems.put(childItems.item(temp).getTextContent(), true);
        }
        return availableItems;
    }
}
