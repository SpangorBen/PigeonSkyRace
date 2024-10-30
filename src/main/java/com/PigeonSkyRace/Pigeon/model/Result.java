package com.PigeonSkyRace.Pigeon.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    @Id
    private String id;
    private int points;
    private int speed;
    private int ranking;
    private int distance;
    private LocalTime time;
    private String flightTime;
    private LocalDateTime arrivalDate;
    private String competitionId;
    private String pigeonId;
}
