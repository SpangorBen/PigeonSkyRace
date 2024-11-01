package com.PigeonSkyRace.Pigeon.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

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
    private double speed;
    private int ranking;
    private double distance;
    private double flightTime;
    private LocalDateTime arrivalDate;

    @DocumentReference
    private Pigeon pigeon;
    @DocumentReference
    private Competition competition;
}
