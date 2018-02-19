package com.tuenti.scandel.domain;

public enum BasketBallPosition {

    G(2, 3, 1),//Guard
    F(2, 2, 2),//Forward
    C(2, 1, 3);//Center

    private int scoredPoints;
    private int reboundsPoints;
    private int assitPoints;

    BasketBallPosition(int scoredPoints, int reboundsPoints, int assitPoints) {
        this.scoredPoints = scoredPoints;
        this.reboundsPoints = reboundsPoints;
        this.assitPoints = assitPoints;
    }

    public static BasketBallPosition getEnum(String value) {
        for (BasketBallPosition v : values()) {
            if (v.name().equalsIgnoreCase(value)) {
                return v;
            }
        }
        return null;
    }

    public int getScoredPoints() {
        return scoredPoints;
    }

    public int getReboundsPoints() {
        return reboundsPoints;
    }

    public int getAssitPoints() {
        return assitPoints;
    }

}
