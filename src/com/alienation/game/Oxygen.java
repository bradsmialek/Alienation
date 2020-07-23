package com.alienation.game;

import java.util.Scanner;

/**
 * Oxygen Class
 */
public class Oxygen {
    public static int oxygen = 40;

    //TODO: MAKE MAX OXYGEN
    public static int getOxygen() {
        return oxygen;
    }

    //Decreases oxygen levels
    public static void minOxygen(int minusOxy) {
        Oxygen.oxygen = Oxygen.oxygen - minusOxy;
        System.out.println(Engine.ANSI_RED + "\n\nOxygen level -10" + Engine.ANSI_RESET);
    }

    //Increases oxygen levels
    public static void incOxygen(int incOxy) {
        Oxygen.oxygen = Oxygen.oxygen + incOxy;
        //TODO: IF OVER 100 SET IT TO MAX
    }

    // checks oxygen levels
    public static void checkOxy(){
        if(Oxygen.getOxygen() == 0){
            System.out.println(Engine.ANSI_RED + "\n\nOxygen depleted..." + Engine.ANSI_RESET); // Better Death
            death();
            System.exit(0);
            //TODO: make start screen to redirect to game start scene instead of exiting
        }
    }

    public static void death(){
        System.out.println("\n" + Engine.ANSI_RED +
                "____    ____  ______    __    __          ___      .______       _______     _______   _______     ___       _______               \n" +
                "\\   \\  /   / /  __  \\  |  |  |  |        /   \\     |   _  \\     |   ____|   |       \\ |   ____|   /   \\     |       \\              \n" +
                " \\   \\/   / |  |  |  | |  |  |  |       /  ^  \\    |  |_)  |    |  |__      |  .--.  ||  |__     /  ^  \\    |  .--.  |             \n" +
                "  \\_    _/  |  |  |  | |  |  |  |      /  /_\\  \\   |      /     |   __|     |  |  |  ||   __|   /  /_\\  \\   |  |  |  |             \n" +
                "    |  |    |  `--'  | |  `--'  |     /  _____  \\  |  |\\  \\----.|  |____    |  '--'  ||  |____ /  _____  \\  |  '--'  | __ __ __ __ \n" +
                "    |__|     \\______/   \\______/     /__/     \\__\\ | _| `._____||_______|   |_______/ |_______/__/     \\__\\ |_______/ (__|__|__|__)\n" +
                "                                                                                                                                   ");
    }

}
