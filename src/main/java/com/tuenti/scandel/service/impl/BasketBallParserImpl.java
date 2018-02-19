package com.tuenti.scandel.service.impl;

import com.tuenti.scandel.domain.BasketBallPosition;
import com.tuenti.scandel.domain.BasketBallScorePoints;
import com.tuenti.scandel.service.BasketBallParser;
import com.tuenti.scandel.service.BasketBallParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.util.stream.Stream;

/**
 * BasketBall parse
 */
@Service
public class BasketBallParserImpl implements BasketBallParser {

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
    public Stream<BasketBallScorePoints> parseBasketBallScorePointsStream(BufferedReader inputReader) {
        return inputReader.lines().map(this::parseBasketBallScorePoints);
    }

    /**
     * Parser of one line
     * @param line
     * @return created bean BasketBallScorePoints from line
     */
    @Override
    public BasketBallScorePoints parseBasketBallScorePoints(String line){
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
            BasketBallPosition fieldPosition = BasketBallPosition.getEnum(cols[4]);
            // score
            int score = Integer.valueOf(cols[5]);
            // rebounds
            int rebounds = Integer.valueOf(cols[6]);
            // assist
            int assist = Integer.valueOf(cols[7]);

            return new BasketBallScorePoints(playerName, playerNickname, team, fieldPosition, score, rebounds, assist);

        } catch (Exception e) {
            throw new RuntimeException("There was a problem parsing this line " + line);
        }
    }

}
