package com.alienation.coregamefiles;

public abstract class Room {
    private int minusOxy = 10; // TODO: Make random number??

    public String getStory() {
        return null;
    }

    public void loadEnvironment() throws Exception {
        Character.checkHealth();
        Oxygen.minOxygen(minusOxy);
        Oxygen.checkOxy();
        System.out.println(getStory());
    }
}


