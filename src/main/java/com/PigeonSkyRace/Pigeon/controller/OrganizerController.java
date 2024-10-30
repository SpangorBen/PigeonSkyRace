package com.PigeonSkyRace.Pigeon.controller;

import com.PigeonSkyRace.Pigeon.model.Competition;
import com.PigeonSkyRace.Pigeon.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/organizer")
public class OrganizerController {
    @Autowired
    private CompetitionService competitionService;

    @PostMapping("/addCompetition")
    public ResponseEntity<?> addCompetition(@RequestBody Competition competition) {
        Competition savedCompetition = competitionService.addCompetition(competition);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompetition);
    }
    @PutMapping("/updateCompetition")
    public ResponseEntity<?> updateCompetition(@RequestParam String id, @RequestParam String badge) {
        Optional<Competition> updatedCompetition = competitionService.updateCompetition(id, badge);
        if (updatedCompetition.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedCompetition);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Competition not found");
        }
    }

}
