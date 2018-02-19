package com.tuenti.scandel.service;


import com.tuenti.scandel.domain.HandBallPosition;
import com.tuenti.scandel.domain.HandBallScorePoints;
import com.tuenti.scandel.service.impl.HandBallParserImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HandBallParserTest {

    private HandBallParser handBallParser = new HandBallParserImpl();

    @Test
    public void parseLine_OK_Test() throws Exception {
        String line = "player 4;nick4;16;Team B;G;1;25";

        //String playerName, String playerNickname, String team, HandBallPosition fieldPosition, int goalsMade, int goalsReceived
        HandBallScorePoints handBallScorePoints = new HandBallScorePoints("player 4", "nick4", "Team B", HandBallPosition.G, 1, 25);

        Assert.assertEquals(handBallScorePoints, handBallParser.parseHandBallScorePoints(line));
    }

    //TODO mas test para probar todala funcionalidad y posibles errores, no realizado por falta de tiempo
}
