package com.tuenti.scandel.service;


import com.tuenti.scandel.domain.BasketBallPosition;
import com.tuenti.scandel.domain.BasketBallScorePoints;
import com.tuenti.scandel.service.impl.BasketBallServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BasketBallServiceTest {

    @Mock
    private BasketBallParser basketBallParser;

    @InjectMocks
    private BasketBallService basketBallService = new BasketBallServiceImpl();

    List<BasketBallScorePoints> basketBallScorePointsLis;

    @Before
    public void setup(){

        ReflectionTestUtils.setField(basketBallService,"basketBallParser",basketBallParser);
        ReflectionTestUtils.setField(basketBallService,"mvpPlayersMap",new HashMap<>());

        basketBallScorePointsLis = new ArrayList<>();
        basketBallScorePointsLis.add(new BasketBallScorePoints("player 1", "nick1",
                "Team B", BasketBallPosition.G, 1, 25, 3));
        basketBallScorePointsLis.add(new BasketBallScorePoints("player 2", "nick2",
                "Team B", BasketBallPosition.G, 2, 25, 2));
        basketBallScorePointsLis.add(new BasketBallScorePoints("player 4", "nick4",
                "Team B", BasketBallPosition.G, 23, 25, 4));

    }

    @Test
    public void calculateMatchPoints_OK_Test() throws Exception {


        Assert.assertEquals("nick4",basketBallService.calculateMatchPoints(basketBallScorePointsLis));

    }

    //TODO mas test para probar todala funcionalidad y posibles errores, no realizado por falta de tiempo
}
