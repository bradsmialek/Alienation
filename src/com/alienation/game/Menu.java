package com.alienation.game;

import java.util.Scanner;

/**
 * Menu For Console
 */
public class Menu {

    private static Actions action;
    public static String word;

    public static void ShowMenu() throws IllegalArgumentException{
        boolean repeat = true;
        Scanner in = new Scanner(System.in);

        //        System.out.println(Character.getHealth() + Oxygen.getOxygen() + Character.getCurrentWeapon());
        System.out.println("Health: 100" + "  " + "Oxygen: 50" + "  " + "Weapon: ");
        System.out.println("What would you like to do?\n");
        System.out.println("You can: Investigate, Open, Eat, Grab, Attack, Read, Swap");
        System.out.println("You can move: N, S, E, W");


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
//    INVESTIGATE, OPEN, EAT, GRAB, ATTACK, READ, SWAP
        switch (action) {
            case INVESTIGATE:
                Room1.Environment();
                break;
            case OPEN:
                System.out.println("do Something with open");
                break;
            case EAT:
                System.out.println("do Something with eat");
                break;
            case GRAB:
                System.out.println("do Something with grab");
                break;
            case ATTACK:
                System.out.println("do Something with attack");
                break;
            case READ:
                System.out.println("do Something with read");
                break;
            case SWAP:
                System.out.println("do Something with swap");
                break;
            case N:
                System.out.println("do Something with N");
                break;
            case E:
                System.out.println("do Something with E");
                break;
            case S:
                System.out.println("do Something with S");
                break;
            case W:
                System.out.println("do Something with W");
                break;
        }

        in.close();
    };

}
