package com.alienation.coregamefiles;

import java.util.Scanner;

/**
 * Receives all user Input
 */
public class Input {

    private static String actionInput;
    private static String item1;
    private static String item2;

    public static void getInput(){

        item1 = null;
        item2 = null;

        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine().toString();
        String delims = "[ ]+";
        String[] tokens = s.split(delims);

        actionInput = tokens[0];

        if (tokens.length == 1){
            item1 = "empty";
            item2 = "empty";
        }

        if (tokens.length == 2){ // eat snickers
            item1 = tokens[1];
            item2 = "empty";

        }if (tokens.length == 3){ // grab oxygen tank
            item1 = tokens[1];
            item2 = item1 + " " + tokens[2];
        }

    }

    public static String getActionInput() {
        return actionInput;
    }

    public static String getItem1() {
        return item1;
    }

    public static String getItem2() {
        return item2;
    }

}
