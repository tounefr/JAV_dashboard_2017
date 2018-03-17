package eu.epitech.java.model;

import java.util.ArrayList;

public class TwitterModel
{
    private boolean isConnected = false;

    private int nbFollowers = 0;

    private int nbTweets = 0;

    private int nbSubscriptions = 0;

    private int nbLikes = 0;

    private ArrayList<String> lastFollowers;

    private ArrayList<TweetModel> lastMentions;

    private ArrayList<TweetModel> lastTweets;

    public int getNbFollowers() {
        return nbFollowers;
    }

    public void setNbFollowers(int nbFollowers) {
        this.nbFollowers = nbFollowers;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public ArrayList<String> getLastFollowers() {
        return lastFollowers;
    }

    public void setLastFollowers(ArrayList<String> lastFollowers) {
        this.lastFollowers = lastFollowers;
    }

    public ArrayList<TweetModel> getLastMentions() {
        return lastMentions;
    }

    public void setLastMentions(ArrayList<TweetModel> lastMentions) {
        this.lastMentions = lastMentions;
    }

    public ArrayList<TweetModel> getLastTweets() {
        return lastTweets;
    }

    public void setLastTweets(ArrayList<TweetModel> lastTweets) {
        this.lastTweets = lastTweets;
    }

    public int getNbTweets() {
        return nbTweets;
    }

    public void setNbTweets(int nbTweets) {
        this.nbTweets = nbTweets;
    }

    public int getNbSubscriptions() {
        return nbSubscriptions;
    }

    public void setNbSubscriptions(int nbSubscriptions) {
        this.nbSubscriptions = nbSubscriptions;
    }

    public int getNbLikes() {
        return nbLikes;
    }

    public void setNbLikes(int nbLikes) {
        this.nbLikes = nbLikes;
    }
}