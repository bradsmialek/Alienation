package com.alienation.game;
import java.util.Scanner;

/**
 * Game Engine
 * This class is used to start the Engine of the game
 */
public class Engine {

<<<<<<< HEAD
    public static void Start(){

=======
    /*************** PUBLIC METHODS  ******************/
    public static void start(){
        Scanner in = new Scanner(System.in);
>>>>>>> 9a21e2f43bed4251c360886f6907a1c6edd2a434
        System.out.println("\n" +
                "░█████╗░██╗░░░░░██╗███████╗███╗░░██╗░█████╗░████████╗██╗░█████╗░███╗░░██╗\n" +
                "██╔══██╗██║░░░░░██║██╔════╝████╗░██║██╔══██╗╚══██╔══╝██║██╔══██╗████╗░██║\n" +
                "███████║██║░░░░░██║█████╗░░██╔██╗██║███████║░░░██║░░░██║██║░░██║██╔██╗██║\n" +
                "██╔══██║██║░░░░░██║██╔══╝░░██║╚████║██╔══██║░░░██║░░░██║██║░░██║██║╚████║\n" +
                "██║░░██║███████╗██║███████╗██║░╚███║██║░░██║░░░██║░░░██║╚█████╔╝██║░╚███║\n" +
                "╚═╝░░╚═╝╚══════╝╚═╝╚══════╝╚═╝░░╚══╝╚═╝░░╚═╝░░░╚═╝░░░╚═╝░╚════╝░╚═╝░░╚══╝\n\n");

<<<<<<< HEAD

//        while (Character.gethealth() > 0 && Oxygen.getLevels() > 0) {
//
//            System.out.println("Guess any letter in the word");
//            System.out.println(Hangman.emptyString);
//            String guess = sc.next();
//            Hangman.guessWords(guess);
//        }


        CapsuleRoom.loadEnvironment();

=======
        CapsuleRoom.loadEnvironment();
>>>>>>> 9a21e2f43bed4251c360886f6907a1c6edd2a434
    }
}
