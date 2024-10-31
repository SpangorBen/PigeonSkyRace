package com.PigeonSkyRace.Pigeon.controller;

import com.PigeonSkyRace.Pigeon.dto.RaceData;
import com.PigeonSkyRace.Pigeon.model.Competition;
import com.PigeonSkyRace.Pigeon.service.CompetitionService;
import com.PigeonSkyRace.Pigeon.util.CsvParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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


    @PostMapping("/results")
    public ResponseEntity<?> uploadRaceData(@RequestParam("file")MultipartFile file){

        try {
            List<RaceData> raceDataList = CsvParserUtil.parseCsvFile(file);
            return ResponseEntity.status(HttpStatus.OK).body(raceDataList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
