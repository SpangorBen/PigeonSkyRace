package com.PigeonSkyRace.Pigeon.model;

import com.PigeonSkyRace.Pigeon.model.enums.TypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Competition {
    @Id
    private String id;
    private String name;
    private String distance;
    private LocalDateTime startDate;
    private String longitude;
    private String latitude;
    private Boolean isOpen;
    private TypeEnum type;
    @DocumentReference
    private List<Result> results;
    @DocumentReference
    private List<Pigeon> pigeons;
}
