package com.PigeonSkyRace.Pigeon.util;

import java.time.Duration;
import java.time.LocalDateTime;

public class FlightTimeUtil {

    public static String calculateFlightTime(LocalDateTime startDate, LocalDateTime arrivalDate) {
        Duration flightTimeDuration =  Duration.between(startDate, arrivalDate);
        return formatDuration(flightTimeDuration);
    }

    public static String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        return hours + ":" + minutes + ":" + seconds;
    }

}
