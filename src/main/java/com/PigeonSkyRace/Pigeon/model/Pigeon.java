package com.PigeonSkyRace.Pigeon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Pigeon {
    @Id
    private String id;
    private String name;
    private int age;
    private String gender;
    private String color;
    @Indexed(unique = true)
    private String badge;
    private String breederId;
    private String competitionId;
}
