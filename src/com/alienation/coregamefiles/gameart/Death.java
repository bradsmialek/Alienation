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

package com.alienation.coregamefiles.gameart;

import com.alienation.coregamefiles.Menu;

import static com.alienation.coregamefiles.gameart.TextColors.*;

/**
 * Created by bradsmialek on Wed - 7/29/20 @ 1:45 AM
 * Death Scene
 */
public class Death {
    public static void death(){
        System.out.println("\n" + ANSI_RED +
                "____    ____  ______    __    __          ___      .______       _______     _______   _______     ___       _______               \n" +
                "\\   \\  /   / /  __  \\  |  |  |  |        /   \\     |   _  \\     |   ____|   |       \\ |   ____|   /   \\     |       \\              \n" +
                " \\   \\/   / |  |  |  | |  |  |  |       /  ^  \\    |  |_)  |    |  |__      |  .--.  ||  |__     /  ^  \\    |  .--.  |             \n" +
                "  \\_    _/  |  |  |  | |  |  |  |      /  /_\\  \\   |      /     |   __|     |  |  |  ||   __|   /  /_\\  \\   |  |  |  |             \n" +
                "    |  |    |  `--'  | |  `--'  |     /  _____  \\  |  |\\  \\----.|  |____    |  '--'  ||  |____ /  _____  \\  |  '--'  | __ __ __ __ \n" +
                "    |__|     \\______/   \\______/     /__/     \\__\\ | _| `._____||_______|   |_______/ |_______/__/     \\__\\ |_______/ (__|__|__|__)\n" +
                "                                                                                                                                   " + ANSI_RESET);
        Menu.StartNewOrQuitGame();
    }
}
