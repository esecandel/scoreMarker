package com.tuenti.scandel.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BasketBallScorePoints {

    @NotEmpty
    private String playerName;
    @NotEmpty
    private String playerNickname;
    @NotEmpty
    private String team;
    @NotNull
    private BasketBallPosition fieldPosition;
    @NotNull
    @Min(value = 0L, message = "The value must be a positive number")
    private int score;
    @NotNull
    @Min(value = 0L, message = "The value must be a positive number")
    private int rebound;
    @NotNull
    @Min(value = 0L, message = "The value must be a positive number")
    private int assist;

    public BasketBallScorePoints() {
    }

    public BasketBallScorePoints(String playerName, String playerNickname, String team, BasketBallPosition fieldPosition, int score, int rebound, int assist) {
        this.playerName = playerName;
        this.playerNickname = playerNickname;
        this.team = team;
        this.fieldPosition = fieldPosition;
        this.score = score;
        this.rebound = rebound;
        this.assist = assist;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerNickname() {
        return playerNickname;
    }

    public void setPlayerNickname(String playerNickname) {
        this.playerNickname = playerNickname;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public BasketBallPosition getFieldPosition() {
        return fieldPosition;
    }

    public void setFieldPosition(BasketBallPosition fieldPosition) {
        this.fieldPosition = fieldPosition;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRebound() {
        return rebound;
    }

    public void setRebound(int rebound) {
        this.rebound = rebound;
    }

    public int getAssist() {
        return assist;
    }

    public void setAssist(int assist) {
        this.assist = assist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasketBallScorePoints)) return false;

        BasketBallScorePoints that = (BasketBallScorePoints) o;

        if (score != that.score) return false;
        if (rebound != that.rebound) return false;
        if (assist != that.assist) return false;
        if (playerName != null ? !playerName.equals(that.playerName) : that.playerName != null) return false;
        if (playerNickname != null ? !playerNickname.equals(that.playerNickname) : that.playerNickname != null)
            return false;
        if (team != null ? !team.equals(that.team) : that.team != null) return false;
        return fieldPosition == that.fieldPosition;
    }

    @Override
    public int hashCode() {
        int result = playerName != null ? playerName.hashCode() : 0;
        result = 31 * result + (playerNickname != null ? playerNickname.hashCode() : 0);
        result = 31 * result + (team != null ? team.hashCode() : 0);
        result = 31 * result + (fieldPosition != null ? fieldPosition.hashCode() : 0);
        result = 31 * result + score;
        result = 31 * result + rebound;
        result = 31 * result + assist;
        return result;
    }
}
