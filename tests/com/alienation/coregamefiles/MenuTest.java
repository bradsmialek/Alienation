package com.alienation.coregamefiles;

import com.alienation.coregamefiles.enums.Rooms;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by bradsmialek on Fri - 7/24/20 @ 12:26 PM
 */
public class MenuTest {
    String currentDirectory = null;
    String item;


    @Before
    public void init(){
        // Get current directory where a text file is
        currentDirectory = System.getProperty("user.dir");
        //System.out.println(currentDirectory);
        currentDirectory = currentDirectory + "/test/";

        Menu menu = new Menu();
    }

    @Test
    public void swap() {
    }

    @Test
    public void read() {
    }

    @Test
    public void run() {
    }

    @Test
    public void attack() {
    }

    @Test
    public void alienAttackOrRun() {
    }

    @Test
    public void alienAttack() {
    }

    @Test
    public void investigate() {
    }

    @Test
    public void open() {
    }

    @Test
    //happy
    public void grab_anItem_should_storeIn_inventory() throws FileNotFoundException {
        File text = new File("/Users/bradsmialek/tlg/java/projects/Alienation/src/grabtoinventory.txt");
        Scanner scnr = new Scanner(text);
        System.out.println(scnr);
        System.out.println("passed");
    }
    @Test
    //fail
    public void grab_an_Inelligible_itemShould_throwError() throws FileNotFoundException {
        File text = new File("/Users/bradsmialek/tlg/java/projects/Alienation/src/grabFail.txt");
        Scanner scnr = new Scanner(text);
        System.out.println(scnr);
        System.out.println("passed");
    }

    @Test
    //happy oxy
    public void grabOxygenTank_shouldIncrease_o2Levels() throws FileNotFoundException{
        Oxygen oxy = new Oxygen();
        Menu menu = new Menu();
        Character character = new Character();
        Rooms currentRoom = character.getCurrentRoom();
//        String line = null;
//        File text = new File("/Users/bradsmialek/tlg/java/projects/Alienation/tests/grabOxygen.txt");
//        Scanner scnr = new Scanner(text);
//        int lineNumber = 1;
//        while(scnr.hasNextLine()){
//            line  = scnr.nextLine();
//            System.out.println("line " + lineNumber + " :" + line);
//            lineNumber++;
//            System.out.println(line.getClass());
//            System.out.println();


//        }
//        Scanner sc = setUpScanner(currentDirectory, "grabOxygen.txt");
//        menu.action = Actions.GRAB;
//        menu.grab(currentRoom);


    }
//    @Test
    //fail oxy
//    public void moveToRoom_should_decrease_o2Levels() throws FileNotFoundException{
//        Oxygen oxy = new Oxygen();
//        Menu menu = new Menu();
//        Character character = new Character();
//        Rooms currentRoom = character.getCurrentRoom();
//        String line = null;
//        File text = new File("/Users/bradsmialek/tlg/java/projects/Alienation/src/grabOxygen.txt");
//        Scanner scnr = new Scanner(text);
//        int lineNumber = 1;
//        while(scnr.hasNextLine()){
//            line  = scnr.nextLine();
//            System.out.println("line " + lineNumber + " :" + line);
//            lineNumber++;
//            System.out.println(line.getClass());
//            System.out.println();

//            send input to method
//            instance of oxygen


//        }
//        menu.grab(currentRoom);
//
//        //getOxygen()
//
//    }

    @Test
    public void eat() {
    }

    @Test
    public void moveRoom() {
    }

    @Test
    public void checkInventory() {
    }

//    private Scanner setUpScanner(String fileDirectory, String filename) {
//        Scanner sc = null;
//        try{
//            sc = new Scanner(new File(fileDirectory + filename));
//        }
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return sc;
//    }
//
//    private void execute(Scanner sc){
//        try {
//            Menu.grab(sc);
//        }
//        catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
}
