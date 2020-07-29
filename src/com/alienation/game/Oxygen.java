package com.alienation.game;

/**
 * Oxygen Class
 */
public class Oxygen {
    public static int oxygen = 50;
    private static final String oTwo = "O\u2082"; // O₂

    //TODO: MAKE MAX OXYGEN
    public static int getOxygen() {
        return oxygen;
    }

//<<<<<<< HEAD
    //Decreases oxygen levels  SETTERS
//    public static void minOxygen(int minusOxy) {
//        Oxygen.oxygen = Oxygen.oxygen - minusOxy;
//        //TODO: wanna discuss with @Brad
//        System.out.println(Engine.ANSI_RED + "\n\n-10 " + oTwo + Engine.ANSI_RESET);
//=======
    //set Oxygen level
    public static void setOxygen(int newOxygen) {
        Oxygen.oxygen = newOxygen;
    }

    //Decreases oxygen levels
    public static void minOxygen(int minusOxy) {
        if(Oxygen.oxygen - minusOxy < 0){
            Oxygen.oxygen = 0;
        } else {
            Oxygen.oxygen = Oxygen.oxygen - minusOxy;
        }
        System.out.println(Engine.ANSI_RED + "-10 " + oTwo + Engine.ANSI_RESET);
//>>>>>>> 1e088c83802101dcef03e237eaf09f2b3d9c626d
    }

    //Increases oxygen levels SETTERS
    public static void incOxygen(int incOxy) {
        Oxygen.oxygen = Oxygen.oxygen + incOxy;
        //TODO: IF OVER 100 SET IT TO MAX
    }

    // checks oxygen levels
    public static void checkOxy(){
        if(Oxygen.getOxygen() == 0){
            System.out.println(Engine.ANSI_RED + "\n\nOxygen depleted..." + Engine.ANSI_RESET); // Better Death
//<<<<<<< HEAD
            System.out.println(Death.death());
            System.exit(0);
        }
    }
}
//=======
//            Menu.death();
//        }
//    }
//}
//>>>>>>> 1e088c83802101dcef03e237eaf09f2b3d9c626d
