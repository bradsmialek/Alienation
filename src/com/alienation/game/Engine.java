package com.alienation.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Game Engine
 */
public class Engine {

    private static String answer;

    public static void Start(){
        Scanner in = new Scanner(System.in);
        System.out.println("\n" +
                "░█████╗░██╗░░░░░██╗███████╗███╗░░██╗░█████╗░████████╗██╗░█████╗░███╗░░██╗\n" +
                "██╔══██╗██║░░░░░██║██╔════╝████╗░██║██╔══██╗╚══██╔══╝██║██╔══██╗████╗░██║\n" +
                "███████║██║░░░░░██║█████╗░░██╔██╗██║███████║░░░██║░░░██║██║░░██║██╔██╗██║\n" +
                "██╔══██║██║░░░░░██║██╔══╝░░██║╚████║██╔══██║░░░██║░░░██║██║░░██║██║╚████║\n" +
                "██║░░██║███████╗██║███████╗██║░╚███║██║░░██║░░░██║░░░██║╚█████╔╝██║░╚███║\n" +
                "╚═╝░░╚═╝╚══════╝╚═╝╚══════╝╚═╝░░╚══╝╚═╝░░╚═╝░░░╚═╝░░░╚═╝░╚════╝░╚═╝░░╚══╝\n\n");

        CapsuleRoom.loadEnvironment();


        in.close(); // CLOSES SCANNER
    }
}
