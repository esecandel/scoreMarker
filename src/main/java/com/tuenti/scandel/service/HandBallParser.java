package com.tuenti.scandel.service;

import com.tuenti.scandel.domain.HandBallScorePoints;

import java.io.BufferedReader;
import java.util.stream.Stream;

public interface HandBallParser {
    Stream<HandBallScorePoints> parseHandBallScorePointsStream(BufferedReader inputReader);

    HandBallScorePoints parseHandBallScorePoints(String line);
}
