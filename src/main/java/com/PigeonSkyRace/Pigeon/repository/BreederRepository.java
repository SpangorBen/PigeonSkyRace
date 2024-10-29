package com.PigeonSkyRace.Pigeon.repository;

import com.PigeonSkyRace.Pigeon.model.Breeder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BreederRepository extends MongoRepository<Breeder, String> {
    Optional<Breeder> findByEmail(String email);
}
