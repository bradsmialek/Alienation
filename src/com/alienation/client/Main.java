package com.alienation.client;

import com.alienation.game.Engine;

/**
 * Main Class
 */
public class Main {
    public static void main(String[] args) {
        try {
            Engine.start();
        } catch (Exception e) {
            System.out.println("Something wrong with the Game!!!");
        }
    }
}
