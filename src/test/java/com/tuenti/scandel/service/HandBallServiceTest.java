package com.tuenti.scandel.service;


import com.tuenti.scandel.domain.HandBallPosition;
import com.tuenti.scandel.domain.HandBallScorePoints;
import com.tuenti.scandel.service.impl.HandBallParserImpl;
import com.tuenti.scandel.service.impl.HandBallServiceImpl;
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
public class HandBallServiceTest {

    @Mock
    private HandBallParser handBallParser;

    @InjectMocks
    private HandBallService handBallService = new HandBallServiceImpl();

    List<HandBallScorePoints> handBallScorePointsLis;

    @Before
    public void setup(){

        ReflectionTestUtils.setField(handBallService,"handBallParser",handBallParser);
        ReflectionTestUtils.setField(handBallService,"mvpPlayersMap",new HashMap<>());

        handBallScorePointsLis = new ArrayList<>();
        handBallScorePointsLis.add(new HandBallScorePoints("player 1", "nick1",
                "Team B", HandBallPosition.G, 1, 25));
        handBallScorePointsLis.add(new HandBallScorePoints("player 2", "nick2",
                "Team B", HandBallPosition.G, 2, 25));
        handBallScorePointsLis.add(new HandBallScorePoints("player 4", "nick4",
                "Team B", HandBallPosition.G, 3, 25));

    }

    @Test
    public void calculateMatchPoints_OK_Test() throws Exception {


        Assert.assertEquals("nick4",handBallService.calculateMatchPoints(handBallScorePointsLis));

    }

    //TODO mas test para probar todala funcionalidad y posibles errores, no realizado por falta de tiempo
}
