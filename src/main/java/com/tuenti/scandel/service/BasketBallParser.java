package com.tuenti.scandel.service;

import com.tuenti.scandel.domain.BasketBallScorePoints;
import com.tuenti.scandel.domain.HandBallScorePoints;

import java.io.BufferedReader;
import java.util.stream.Stream;

public interface BasketBallParser {
    Stream<BasketBallScorePoints> parseBasketBallScorePointsStream(BufferedReader inputReader);

    BasketBallScorePoints parseBasketBallScorePoints(String line);
}
