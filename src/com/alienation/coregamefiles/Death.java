package com.alienation.coregamefiles;

import static com.alienation.coregamefiles.TextColors.*;

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
