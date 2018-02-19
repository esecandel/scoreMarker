package com.tuenti.scandel.service;


import com.tuenti.scandel.domain.BasketBallPosition;
import com.tuenti.scandel.domain.BasketBallScorePoints;
import com.tuenti.scandel.service.impl.BasketBallParserImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BasketBallParserTest {

    private BasketBallParser basketBallParser = new BasketBallParserImpl();

    @Test
    public void parseLine_OK_Test() throws Exception {
        String line = "player 1;nick1;4;Team A;G;10;2;7";

        //String playerName, String playerNickname, String team, BasketBallPosition fieldPosition, int score, int rebound, int assist
        BasketBallScorePoints basketBallScorePoints = new BasketBallScorePoints("player 1",
                "nick1", "Team A", BasketBallPosition.G, 10, 2, 7);

        Assert.assertEquals(basketBallScorePoints, basketBallParser.parseBasketBallScorePoints(line));
    }

    //TODO mas test para probar todala funcionalidad y posibles errores, no realizado por falta de tiempo
}
