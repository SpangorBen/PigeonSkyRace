package com.PigeonSkyRace.Pigeon.util;

import com.PigeonSkyRace.Pigeon.model.Result;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SpeedCalculatorUtil {

    public static double calculateSpeed(double flightTime, double averageDistance) {
        return averageDistance / flightTime;
    }

    public static double averageDistance (List<Result> result) {
        double averageDistance = 0;
        for (Result results: result){
            averageDistance += results.getDistance();
        }
        return averageDistance / result.size();
    }

//    public static void main(String[] args) {
//        LocalDateTime startDate = LocalDateTime.parse("2024-10-30T08:30:00");
//        LocalDateTime arrivalDate = LocalDateTime.parse("2024-10-30T12:15:00");
//        double flightTime = FlightTimeUtil.calculateFlightTime(startDate, arrivalDate);
//        System.out.println("Flight Time: " + flightTime);
//        Result result1 = new Result();
//        result1.setId("6720b6910320027b980be286");
//        result1.setDistance(25);
//        Result result2 = new Result();
//        result2.setDistance(16);
//        Result result3 = new Result();
//        result3.setDistance(56);
//        List<Result> results = new ArrayList<>();
//        results.add(result1);
//        results.add(result2);
//        results.add(result3);
//        double distance = averageDistance(results);
//        for (Result result: results){
//            System.out.println("distance1: " + result.getDistance());
//        }
//        System.out.println("Distance: " + distance);
//        double speed = calculateSpeed(flightTime, distance);
//        System.out.println("Speed: " + speed + " km/min");
//    }
}
