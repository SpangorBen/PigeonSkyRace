package com.PigeonSkyRace.Pigeon.service.impl;

import com.PigeonSkyRace.Pigeon.model.Competition;
import com.PigeonSkyRace.Pigeon.model.Pigeon;
import com.PigeonSkyRace.Pigeon.repository.CompetitionRepository;
import com.PigeonSkyRace.Pigeon.repository.PigeonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetitionServiceImpl implements com.PigeonSkyRace.Pigeon.service.CompetitionService {

    @Autowired
    private CompetitionRepository competitionRepository;
    @Autowired
    private PigeonRepository pigeonRepository;

    @Override
    public Competition addCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

    @Override
    public Optional<Competition> updateCompetition(String id, String badge) {
//        List<Pigeon> pigeons = pigeonRepository.findByBadge(badge);
//        Optional<Competition> competitionResult = competitionRepository.findById(id);
//
//        if (competitionResult.isPresent() && competitionResult.get().getIsOpen().equals(true)) {
//            Competition competition = competitionResult.get();
//            competition.setPigeons(pigeons);
//            competitionRepository.save(competition);
//            return Optional.of(competition);
//        } else {
//            return Optional.empty();
//        }
        return Optional.empty();
    }

    @Override
    public Competition getCompetitionById(String competitionId) {
        return competitionRepository.findById(competitionId).orElseThrow(() -> new IllegalArgumentException("Competition not found"));
    }
}
