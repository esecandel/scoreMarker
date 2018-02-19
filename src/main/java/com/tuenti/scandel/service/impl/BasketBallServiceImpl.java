package com.tuenti.scandel.service.impl;

import com.tuenti.scandel.domain.BasketBallScorePoints;
import com.tuenti.scandel.service.BasketBallService;
import com.tuenti.scandel.service.BasketBallParser;
import com.tuenti.scandel.service.BasketBallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BasketBallServiceImpl implements BasketBallService {

    private static final Logger log = LoggerFactory.getLogger(BasketBallServiceImpl.class);

    //Susceptible to move into properties file
    private static final int ADDITIONAL_POINTS = 10;
    private static final int NO_POINTS = 0;


    private Map<String, Integer> mvpPlayersMap;

    @Autowired
    private BasketBallParser basketBallParser;

    @PostConstruct
    private void init() {
        mvpPlayersMap = new HashMap<>();
    }


    /**
     * Parse file into a list of score points and calculate player's points
     *
     * @param inputStream
     * @return
     */
    @Override
    public String parseMatch(InputStream inputStream) {

        Stream<BasketBallScorePoints> stream = basketBallParser.parseBasketBallScorePointsStream(
                new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream))));

        List<BasketBallScorePoints> basketBallScorePointsList = stream.collect(Collectors.toList());

        return this.calculateMatchPoints(basketBallScorePointsList);
    }

    /**
     * Calculate score points of all player's match, and add the points to global MVP count.
     *
     * @param basketBallScorePointsList
     * @return
     */
    @Override
    public String calculateMatchPoints(List<BasketBallScorePoints> basketBallScorePointsList) {

        String winnerTeam = this.whoIsWinnerTeam(basketBallScorePointsList);

        int playerPoints;
        Map<String, Integer> playersScoreMatchMap = new HashMap<>();
        for (BasketBallScorePoints basketBallScorePoints : basketBallScorePointsList) {

            //Avoid duplicity of a player in a match
            if (playersScoreMatchMap.containsKey(basketBallScorePoints.getPlayerNickname())) {
                throw new RuntimeException("Duplicate player in same match " + basketBallScorePoints.getPlayerNickname());
            }

            //Apply formula
            playerPoints = this.basketBallFormula(basketBallScorePoints, winnerTeam);

            log.info("Player {} has {} points at this match", basketBallScorePoints.getPlayerNickname(), playerPoints);

            //Add player points to avoid duplicity
            playersScoreMatchMap.put(basketBallScorePoints.getPlayerNickname(), playerPoints);

            //Add player point to total MVP points
            if (mvpPlayersMap.containsKey(basketBallScorePoints.getPlayerNickname())) {
                mvpPlayersMap.put(basketBallScorePoints.getPlayerNickname(),
                        mvpPlayersMap.get(basketBallScorePoints.getPlayerNickname()) + playerPoints);
            } else {
                mvpPlayersMap.put(basketBallScorePoints.getPlayerNickname(), playerPoints);
            }

        }


        return this.whoIsMVP();
    }

    /**
     * Calculate score points of a player's match
     *
     * E.g. a player playing as center with 10 scored points, 5 rebounds and no assists will be granted
     25 rating points (10*2 + 5*1 + 0*3 ).
     *
     * @param basketBallScorePoints
     * @param winnerTeam
     * @return total points of this player in a match
     */
    private int basketBallFormula(BasketBallScorePoints basketBallScorePoints, String winnerTeam) {
        return 10 * basketBallScorePoints.getScore() +
                5 * basketBallScorePoints.getRebound() +
                3 * basketBallScorePoints.getAssist() +
                (winnerTeam.equals(basketBallScorePoints.getTeam()) ? ADDITIONAL_POINTS : NO_POINTS);
    }

    /**
     * Add all scores, by team, to know who is the winner team
     *
     * @param basketBallScorePointsList
     * @return winner team
     */
    private String whoIsWinnerTeam(List<BasketBallScorePoints> basketBallScorePointsList) {

        Map<String, Integer> teamGoalsMatchMap = new HashMap<>();
        for (BasketBallScorePoints basketBallScorePoints : basketBallScorePointsList) {

            //Add to scores team map the scores of this player
            if (teamGoalsMatchMap.containsKey(basketBallScorePoints.getTeam())) {
                teamGoalsMatchMap.put(basketBallScorePoints.getTeam(),
                        teamGoalsMatchMap.get(basketBallScorePoints.getTeam()) + basketBallScorePoints.getScore());
            } else {
                teamGoalsMatchMap.put(basketBallScorePoints.getTeam(), basketBallScorePoints.getScore());
            }
        }

        String winner = null;
        for (String team : teamGoalsMatchMap.keySet()) {
            log.debug("Team {}: {} scores", team, teamGoalsMatchMap.get(team));
            winner = (winner == null) ? team : (teamGoalsMatchMap.get(team) > teamGoalsMatchMap.get(winner)) ? team : winner;
        }

        log.info("Winner team: {}", winner);
        return winner;
    }


    /**
     * Show who is the MVP player at this moment
     *
     * @return MVP player right now
     */
    private String whoIsMVP() {
        int highestValue = 0;
        String mvp = null;
        for (String player : mvpPlayersMap.keySet()) {
            if (highestValue < mvpPlayersMap.get(player)) {
                highestValue = mvpPlayersMap.get(player);
                mvp = player;
            }
        }
        log.info("MVP player: {} with {} points", mvp, highestValue);
        return mvp;
    }
}
