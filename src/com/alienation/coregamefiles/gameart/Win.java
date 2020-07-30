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

import java.util.concurrent.TimeUnit;
import static com.alienation.coregamefiles.gameart.TextColors.*;

/**
 * Created by bradsmialek on Wed - 7/29/20 @ 8:03 AM
 * edited by brucebawest on Wed - 7/29/20 @ 3:29 PM
 */
public class Win {
    public static void win(){ //TODO: replace ASCII for "You Won"
        System.out.println("\n" + ANSI_GREEN +
                "██╗░░░██╗░█████╗░██╗░░░██╗░░░░██╗░░░░░░░██╗░█████╗░███╗░░██╗░░░\n" +
                "╚██╗░██╔╝██╔══██╗██║░░░██║░░░░██║░░██╗░░██║██╔══██╗████╗░██║░░░\n" +
                "░╚████╔╝░██║░░██║██║░░░██║░░░░╚██╗████╗██╔╝██║░░██║██╔██╗██║░░░\n" +
                "░░╚██╔╝░░██║░░██║██║░░░██║░░░░░████╔═████║░██║░░██║██║╚████║░░░\n" +
                "░░░██║░░░╚█████╔╝╚██████╔╝░░░░░╚██╔╝░╚██╔╝░╚█████╔╝██║░╚███║██╗\n" +
                "░░░╚═╝░░░░╚════╝░░╚═════╝░░░░░░░╚═╝░░░╚═╝░░░╚════╝░╚═╝░░╚══╝╚═╝\n" +
                "\n" +
                "░█████╗░░█████╗░███╗░░██╗░██████╗░██████╗░░█████╗░████████╗░██████╗██╗██╗\n" +
                "██╔══██╗██╔══██╗████╗░██║██╔════╝░██╔══██╗██╔══██╗╚══██╔══╝██╔════╝██║██║\n" +
                "██║░░╚═╝██║░░██║██╔██╗██║██║░░██╗░██████╔╝███████║░░░██║░░░╚█████╗░██║██║\n" +
                "██║░░██╗██║░░██║██║╚████║██║░░╚██╗██╔══██╗██╔══██║░░░██║░░░░╚═══██╗╚═╝╚═╝\n" +
                "╚█████╔╝╚█████╔╝██║░╚███║╚██████╔╝██║░░██║██║░░██║░░░██║░░░██████╔╝██╗██╗\n" +
                "░╚════╝░░╚════╝░╚═╝░░╚══╝░╚═════╝░╚═╝░░╚═╝╚═╝░░╚═╝░░░╚═╝░░░╚═════╝░╚═╝╚═╝" + ANSI_RESET);

        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("You feel movement in your belly...");
            Menu.StartNewOrQuitGame();
        } catch (InterruptedException e) {
            System.out.println("Something wrong with the Game!!!");
        }
    }
}