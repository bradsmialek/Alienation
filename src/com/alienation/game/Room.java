package com.alienation.game;

public abstract class Room {
    private int minusOxy = 10; // TODO: Make random number??

    public abstract String getStory();

    public void loadEnvironment(){
        Oxygen.minOxygen(minusOxy);
        Oxygen.checkOxy();
        System.out.println(getStory());
    }
}
