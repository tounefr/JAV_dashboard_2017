package eu.epitech.java.model;

import java.util.ArrayList;

public class FacebookModel {

    private boolean isConnected = false;

    private ArrayList<String> friends;

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }
}
