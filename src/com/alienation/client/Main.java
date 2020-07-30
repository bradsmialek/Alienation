package com.alienation.client;

import com.alienation.coregamefiles.Banner;
import com.alienation.enginefiles.Engine;

/**
 * Main Class
 */
public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(Banner.getBanner());
            Engine.start();
        } catch (Exception e) {
            System.out.println("Something wrong with the Game!!!");
        }
    }
}
