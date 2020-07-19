package com.alienation.game;
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
        Scanner in = new Scanner(System.in);
        System.out.println("\n" + ANSI_GREEN +
                "░█████╗░██╗░░░░░██╗███████╗███╗░░██╗░█████╗░████████╗██╗░█████╗░███╗░░██╗\n" +
                "██╔══██╗██║░░░░░██║██╔════╝████╗░██║██╔══██╗╚══██╔══╝██║██╔══██╗████╗░██║\n" +
                "███████║██║░░░░░██║█████╗░░██╔██╗██║███████║░░░██║░░░██║██║░░██║██╔██╗██║\n" +
                "██╔══██║██║░░░░░██║██╔══╝░░██║╚████║██╔══██║░░░██║░░░██║██║░░██║██║╚████║\n" +
                "██║░░██║███████╗██║███████╗██║░╚███║██║░░██║░░░██║░░░██║╚█████╔╝██║░╚███║\n" +
                "╚═╝░░╚═╝╚══════╝╚═╝╚══════╝╚═╝░░╚══╝╚═╝░░╚═╝░░░╚═╝░░░╚═╝░╚════╝░╚═╝░░╚══╝\n\n" + ANSI_RESET);

        CapsuleRoom.loadEnvironment();
    }
}
