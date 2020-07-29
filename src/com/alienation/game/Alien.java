package com.alienation.game;

import java.util.*;

/**
 * Alien Class
 */
public class Alien {
    private static Map<String,Map<String,Integer>> alienTypes = new HashMap<String,Map<String,Integer>>();

    // Get the alien
    public static Map<String,Map<String,Integer>> getAliens(){
        if(alienTypes.size() == 0) {
            Map<String, Integer> pointsMap = new HashMap<>();
            pointsMap.put("HP", 4);
            pointsMap.put("DP", 1);
            alienTypes.put("Vermin", pointsMap);

            pointsMap = new HashMap<>();
            pointsMap.put("HP", 6);
            pointsMap.put("DP", 3);
            alienTypes.put("Canine", pointsMap);

            pointsMap = new HashMap<>();
            pointsMap.put("HP", 10);
            pointsMap.put("DP", 5);
            alienTypes.put("Humanoid", pointsMap);

            pointsMap = new HashMap<>();
            pointsMap.put("HP", 50);
            pointsMap.put("DP", 10);
            alienTypes.put("Superhumanoid", pointsMap);
        }
        return alienTypes;
    }


    /*************** GETTER - SETTER METHODS  ******************/
    //Get HP/DP points
    public static int getPoints(String alienType,String key){
        return alienTypes.get(alienType).get(key);
    }

    //Set HP/DP points
    public static void setPoints(String alienType, String key, int points){
        alienTypes.get(alienType).replace(key, points);
    }
    //TODO: build in weakness and immunity
}

