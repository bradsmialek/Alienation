package com.alienation.client;

import com.alienation.enginefiles.Engine;

import static com.alienation.gameart.Banner.*;

/**
 * Main Class
 */
public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(getBanner());
            Engine.start();
        } catch (Exception e) {
            System.out.println("Something wrong with the Game!!!");
        }
    }
}
