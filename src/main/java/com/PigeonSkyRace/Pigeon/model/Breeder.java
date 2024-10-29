package com.PigeonSkyRace.Pigeon.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Breeder {
    @Id
    private String id;
    private String name;
    private String latitude;
    private String longitude;
    private String password;
    private String email;
    @DocumentReference
    private List<Pigeon> pigeons;
}
