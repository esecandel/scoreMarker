package com.tuenti.scandel.domain;

public enum HandBallPosition {

    G(50, 5, -2),
    F(20, 1, -1);

    private int initialRatingPoints;
    private int goalMadePoints;
    private int goalReceivedPoints;

    HandBallPosition(int initialRatingPoints, int goalMadePoints, int goalReceivedPoints) {
        this.initialRatingPoints = initialRatingPoints;
        this.goalMadePoints = goalMadePoints;
        this.goalReceivedPoints = goalReceivedPoints;
    }

    public static HandBallPosition getEnum(String value) {
        for (HandBallPosition v : values()) {
            if (v.name().equalsIgnoreCase(value)) {
                return v;
            }
        }
        return null;
    }

    public int getInitialRatingPoints() {
        return initialRatingPoints;
    }

    public int getGoalMadePoints() {
        return goalMadePoints;
    }

    public int getGoalReceivedPoints() {
        return goalReceivedPoints;
    }
}
