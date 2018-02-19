package com.tuenti.scandel.service;

import com.tuenti.scandel.domain.HandBallScorePoints;

import java.io.InputStream;
import java.util.List;

public interface HandBallService {

    String parseMatch(InputStream inputStream);

    String calculateMatchPoints(List<HandBallScorePoints> handBallScorePointsList);
}
