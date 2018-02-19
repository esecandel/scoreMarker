package com.tuenti.scandel.service.impl;

import com.tuenti.scandel.domain.HandBallPosition;
import com.tuenti.scandel.domain.HandBallScorePoints;
import com.tuenti.scandel.service.HandBallParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * HandBall parse
 */
@Service
public class HandBallParserImpl implements HandBallParser {

    /**
     * Delimitador de columnas en el CSV.
     */
    private static final String COLUMN_DELIMITER = ";";

    /**
     * Reader ofline sin stream
     * @param inputReader
     * @return
     */
    @Override
    public Stream<HandBallScorePoints> parseHandBallScorePointsStream(BufferedReader inputReader) {
        return inputReader.lines().map(this::parseHandBallScorePoints);
    }

    /**
     * Parser of one line
     * @param line
     * @return created bean HandBallScorePoints from line
     */
    @Override
    public HandBallScorePoints parseHandBallScorePoints(String line){
        try {
            // No uso String.split para evitar la regex, por rendimiento
            String[] cols = StringUtils.splitPreserveAllTokens(line, COLUMN_DELIMITER);

            // playerName
            String playerName = cols[0];
            // playerNickname
            String playerNickname = cols[1];
            // number
            String number = cols[2];
            // team
            String team = cols[3];
            // fieldPosition
            HandBallPosition fieldPosition = HandBallPosition.getEnum(cols[4]);
            // goalsMade
            int goalsMade = Integer.valueOf(cols[5]);
            // goalsReceived
            int goalsReceived = Integer.valueOf(cols[6]);

            return new HandBallScorePoints(playerName, playerNickname, team, fieldPosition, goalsMade, goalsReceived);

        } catch (Exception e) {
            throw new RuntimeException("There was a problem parsing this line " + line);
        }
    }

}
