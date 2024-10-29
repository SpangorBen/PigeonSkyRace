package com.PigeonSkyRace.Pigeon.repository;

import com.PigeonSkyRace.Pigeon.model.Competition;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompetitionRepository extends MongoRepository<Competition, String> {
}
