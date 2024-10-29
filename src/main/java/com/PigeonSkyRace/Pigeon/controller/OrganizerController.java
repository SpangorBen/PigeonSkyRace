package com.PigeonSkyRace.Pigeon.controller;

import com.PigeonSkyRace.Pigeon.model.Competition;
import com.PigeonSkyRace.Pigeon.service.CompetitionInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class OrganizerController {
    @Autowired
    private CompetitionInterface competitionService;
    @PostMapping("/addCompetition")
    public ResponseEntity<?> addCompetition(@RequestBody Competition competition) {
        Competition savedCompetition = competitionService.addCompetition(competition);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompetition);
    }
}
