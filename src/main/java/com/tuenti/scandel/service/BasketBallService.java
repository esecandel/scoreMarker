package com.tuenti.scandel.service;

import com.tuenti.scandel.domain.BasketBallScorePoints;
import com.tuenti.scandel.domain.HandBallScorePoints;

import java.io.InputStream;
import java.util.List;

public interface BasketBallService {

    String parseMatch(InputStream inputStream);

    String calculateMatchPoints(List<BasketBallScorePoints> basketBallScorePointsList);
}
