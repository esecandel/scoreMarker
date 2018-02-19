package com.tuenti.scandel.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class HandBallScorePoints {

    @NotEmpty
    private String playerName;
    @NotEmpty
    private String playerNickname;
    @NotEmpty
    private String team;
    @NotNull
    private HandBallPosition fieldPosition;
    @NotNull
    @Min(value = 0L, message = "The value must be a positive number")
    private int goalsMade;
    @NotNull
    @Min(value = 0L, message = "The value must be a positive number")
    private int goalsReceived;

    public HandBallScorePoints() {
    }

    public HandBallScorePoints(String playerName, String playerNickname, String team, HandBallPosition fieldPosition, int goalsMade, int goalsReceived) {
        this.playerName = playerName;
        this.playerNickname = playerNickname;
        this.team = team;
        this.fieldPosition = fieldPosition;
        this.goalsMade = goalsMade;
        this.goalsReceived = goalsReceived;
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

    public HandBallPosition getFieldPosition() {
        return fieldPosition;
    }

    public void setFieldPosition(HandBallPosition fieldPosition) {
        this.fieldPosition = fieldPosition;
    }

    public int getGoalsMade() {
        return goalsMade;
    }

    public void setGoalsMade(int goalsMade) {
        this.goalsMade = goalsMade;
    }

    public int getGoalsReceived() {
        return goalsReceived;
    }

    public void setGoalsReceived(int goalsReceived) {
        this.goalsReceived = goalsReceived;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HandBallScorePoints)) return false;

        HandBallScorePoints that = (HandBallScorePoints) o;

        if (goalsMade != that.goalsMade) return false;
        if (goalsReceived != that.goalsReceived) return false;
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
        result = 31 * result + goalsMade;
        result = 31 * result + goalsReceived;
        return result;
    }
}
