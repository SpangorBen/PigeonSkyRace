package com.PigeonSkyRace.Pigeon.service.impl;

import com.PigeonSkyRace.Pigeon.model.Competition;
import com.PigeonSkyRace.Pigeon.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetitionServiceImpl implements com.PigeonSkyRace.Pigeon.service.CompetitionService {
    @Autowired
    private CompetitionRepository competitionRepository;
    @Override
    public Competition addCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }
}
