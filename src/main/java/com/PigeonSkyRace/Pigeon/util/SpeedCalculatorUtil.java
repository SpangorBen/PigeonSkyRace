package com.PigeonSkyRace.Pigeon.util;

import com.PigeonSkyRace.Pigeon.model.Result;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SpeedCalculatorUtil {

    public static double calculateSpeed(double flightTime, double distance, double coefficient) {
        if (flightTime == 0) {
            return 0;
        }

        double speed = distance / flightTime;

        return coefficient / speed;
    }

    public static double averageDistance (List<Result> result) {
        double averageDistance = 0;
        for (Result results: result){
            averageDistance += results.getDistance();
        }
        return averageDistance / result.size();
    }

    public static double calculateCoefficient(double distance, double averageDistance) {
        double coefficient = 0;
        coefficient = averageDistance / distance;
        return coefficient;
    }

}
