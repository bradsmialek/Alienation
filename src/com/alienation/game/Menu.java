package com.alienation.game;

/**
 * Menu For Console
 */
public class Menu {

    /*************** PRIVATE VARIABLE DECLARATIONS  ******************/
    private static String actionQuestion = "What would you like to do?\n" + ">";
    private static String actions = "You can: Investigate, Open, Eat, Store, Attack, Read";
    private static String directions = "You can move: N, S, E, W";
    private static String answer;


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
}
