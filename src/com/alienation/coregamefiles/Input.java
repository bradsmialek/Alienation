/*
 *      Alienation
 *      TLG Learning: Capstone Project
 *      Originally Created by Team Alienation &&
 *      Edited by Team Capstone Mafia
 *      https://github.com/Capstone-Mafia
 *
 *      Team Alienation Members:
 *      Brad Smialek (https://github.com/bradsmialek)
 *      Dhruti Kosta (https://github.com/dhruti-kosta)
 *      Terrell Douglas (https://github.com/Dougie105)
 *      Original project repo:
 *      https://github.com/bradsmialek/Alienation
 *
 *      Capstone Mafia Members:
 *      Bruce West (https://github.com/BruceBAWest)
 *      Gurinder Batth (https://github.com/GurinderB)
 *      Daeun Lok (https://github.com/koreareefclub)
 *      Capstone Mafia fork:
 *      https://github.com/Capstone-Mafia/Alienation
 */

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
