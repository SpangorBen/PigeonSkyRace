package com.PigeonSkyRace.Pigeon.service.impl;

import com.PigeonSkyRace.Pigeon.model.Competition;
import com.PigeonSkyRace.Pigeon.repository.CompetitionRepository;
import com.PigeonSkyRace.Pigeon.service.CompetitionInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetitionService implements CompetitionInterface {
    @Autowired
    private CompetitionRepository competitionRepository;
    @Override
    public Competition addCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }
}
