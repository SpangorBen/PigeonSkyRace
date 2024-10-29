package com.PigeonSkyRace.Pigeon.repository;

import com.PigeonSkyRace.Pigeon.model.Breeder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BreederRepository extends MongoRepository<Breeder, String> {
}
