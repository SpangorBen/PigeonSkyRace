package com.PigeonSkyRace.Pigeon.service;

import com.PigeonSkyRace.Pigeon.model.Competition;

import java.util.Optional;

public interface CompetitionService {
    Competition addCompetition(Competition competition);
    Optional<Competition> updateCompetition(String id, String badge);
}
