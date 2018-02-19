package com.tuenti.scandel.service.impl;

import com.tuenti.scandel.domain.HandBallScorePoints;
import com.tuenti.scandel.service.HandBallParser;
import com.tuenti.scandel.service.HandBallService;
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
public class HandBallServiceImpl implements HandBallService {

    private static final Logger log = LoggerFactory.getLogger(HandBallServiceImpl.class);

    //Susceptible to move into properties file
    private static final int ADDITIONAL_POINTS = 10;
    private static final int NO_POINTS = 0;


    private Map<String, Integer> mvpPlayersMap;

    @Autowired
    private HandBallParser handBallParser;

    @PostConstruct
    public void init() {
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

        Stream<HandBallScorePoints> stream = handBallParser.parseHandBallScorePointsStream(
                new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream))));

        List<HandBallScorePoints> handBallScorePointsList = stream.collect(Collectors.toList());

        return this.calculateMatchPoints(handBallScorePointsList);
    }

    /**
     * Calculate score points of all player's match, and add the points to global MVP count.
     *
     * @param handBallScorePointsList
     * @return
     */
    @Override
    public String calculateMatchPoints(List<HandBallScorePoints> handBallScorePointsList) {

        String winnerTeam = this.whoIsWinnerTeam(handBallScorePointsList);

        int playerPoints;
        Map<String, Integer> playersScoreMatchMap = new HashMap<>();
        for (HandBallScorePoints handBallScorePoints : handBallScorePointsList) {

            //Avoid duplicity of a player in a match
            if (playersScoreMatchMap.containsKey(handBallScorePoints.getPlayerNickname())) {
                throw new RuntimeException("Duplicate player in same match " + handBallScorePoints.getPlayerNickname());
            }

            //Apply formula
            playerPoints = this.handBallFormula(handBallScorePoints, winnerTeam);

            log.info("Player {} has {} points at this match", handBallScorePoints.getPlayerNickname(), playerPoints);

            //Add player points to avoid duplicity
            playersScoreMatchMap.put(handBallScorePoints.getPlayerNickname(), playerPoints);

            //Add player point to total MVP points
            if (mvpPlayersMap.containsKey(handBallScorePoints.getPlayerNickname())) {
                mvpPlayersMap.put(handBallScorePoints.getPlayerNickname(),
                        mvpPlayersMap.get(handBallScorePoints.getPlayerNickname()) + playerPoints);
            } else {
                mvpPlayersMap.put(handBallScorePoints.getPlayerNickname(), playerPoints);
            }

        }


        return this.whoIsMVP();
    }

    /**
     * Calculate score points of a player's match
     *
     * E.g. a player playing as goalkeeper with 1 goals made and 10 received will be granted 35 rating
     * points (50 + 1*5 - 10*2 = 35).
     *
     * @param handBallScorePoints
     * @param winnerTeam
     * @return total points of this player in a match
     */
    private int handBallFormula(HandBallScorePoints handBallScorePoints, String winnerTeam) {
        return handBallScorePoints.getFieldPosition().getInitialRatingPoints() +
                handBallScorePoints.getGoalsMade() * handBallScorePoints.getFieldPosition().getGoalMadePoints() -
                handBallScorePoints.getGoalsReceived() * handBallScorePoints.getFieldPosition().getGoalReceivedPoints() +
                (winnerTeam.equals(handBallScorePoints.getTeam()) ? ADDITIONAL_POINTS : NO_POINTS);
    }

    /**
     * Add all goals, by team, to know who is the winner team
     *
     * @param handBallScorePointsList
     * @return winner team
     */
    private String whoIsWinnerTeam(List<HandBallScorePoints> handBallScorePointsList) {

        Map<String, Integer> teamGoalsMatchMap = new HashMap<>();
        for (HandBallScorePoints handBallScorePoints : handBallScorePointsList) {

            //Add to goals team map the goals of this player
            if (teamGoalsMatchMap.containsKey(handBallScorePoints.getTeam())) {
                teamGoalsMatchMap.put(handBallScorePoints.getTeam(),
                        teamGoalsMatchMap.get(handBallScorePoints.getTeam()) + handBallScorePoints.getGoalsMade());
            } else {
                teamGoalsMatchMap.put(handBallScorePoints.getTeam(), handBallScorePoints.getGoalsMade());
            }
        }

        String winner = null;
        for (String team : teamGoalsMatchMap.keySet()) {
            log.debug("Team {}: {} goals", team, teamGoalsMatchMap.get(team));
            winner = (winner == null)
                    ? team : (teamGoalsMatchMap.get(team) > teamGoalsMatchMap.get(winner))
                                ? team : winner;
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
