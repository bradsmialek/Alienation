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
//<<<<<<< HEAD
//
//    public static void setT3Hp(int t3Hp) {
//        Alien.t3Hp = t3Hp;
//    }
//
//    public static int getT4Hp() {
//        return t4Hp;
//    }
//
//    public static void setT4Hp(int t4Hp) {
//        Alien.t4Hp = t4Hp;
//    }
//
//    public static int getT1Dmg() {
//        return t1Dmg;
//    }
//
//    public static int getT2Dmg() {
//        return t2Dmg;
//    }
//
//    public static int getT3Dmg() {
//        return t3Dmg;
//    }
//
//    public static int getT4Dmg() {
//        return t4Dmg;
//    }
//
//=======
//    //TODO: build in weakness and immunity
//>>>>>>> 1e088c83802101dcef03e237eaf09f2b3d9c626d
}

