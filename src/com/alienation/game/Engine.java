package com.alienation.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Game Engine
 */
public class Engine {

    public static void Start(){

        System.out.println("\n" +
                "░█████╗░██╗░░░░░██╗███████╗███╗░░██╗░█████╗░████████╗██╗░█████╗░███╗░░██╗\n" +
                "██╔══██╗██║░░░░░██║██╔════╝████╗░██║██╔══██╗╚══██╔══╝██║██╔══██╗████╗░██║\n" +
                "███████║██║░░░░░██║█████╗░░██╔██╗██║███████║░░░██║░░░██║██║░░██║██╔██╗██║\n" +
                "██╔══██║██║░░░░░██║██╔══╝░░██║╚████║██╔══██║░░░██║░░░██║██║░░██║██║╚████║\n" +
                "██║░░██║███████╗██║███████╗██║░╚███║██║░░██║░░░██║░░░██║╚█████╔╝██║░╚███║\n" +
                "╚═╝░░╚═╝╚══════╝╚═╝╚══════╝╚═╝░░╚══╝╚═╝░░╚═╝░░░╚═╝░░░╚═╝░╚════╝░╚═╝░░╚══╝\n\n");


//        while (Character.gethealth() > 0 && Oxygen.getLevels() > 0) {
//
//            System.out.println("Guess any letter in the word");
//            System.out.println(Hangman.emptyString);
//            String guess = sc.next();
//            Hangman.guessWords(guess);
//        }


        CapsuleRoom.loadEnvironment();

    }
}
