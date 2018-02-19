package com.tuenti.scandel.domain;

public class PlayerScorePoints {

    /*Unique identifier*/
    private String playerNickname;
    /*total points of a match or like MVP*/
    private int points;

    public String getPlayerNickname() {
        return playerNickname;
    }

    public void setPlayerNickname(String playerNickname) {
        this.playerNickname = playerNickname;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "PlayerScorePoints{" +
                "playerNickname='" + playerNickname + '\'' +
                ", points=" + points +
                '}';
    }
}
