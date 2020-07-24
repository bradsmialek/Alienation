package com.alienation.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Recieves all user Input
 */
public class Input {
    public static void main(String[] args) {
        try {
            File myObj = new File("grabFail.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
