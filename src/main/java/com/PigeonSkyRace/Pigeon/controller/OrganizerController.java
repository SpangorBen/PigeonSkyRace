package com.PigeonSkyRace.Pigeon.controller;

import com.PigeonSkyRace.Pigeon.dto.RaceData;
import com.PigeonSkyRace.Pigeon.model.Competition;
import com.PigeonSkyRace.Pigeon.model.Result;
import com.PigeonSkyRace.Pigeon.service.CompetitionService;
import com.PigeonSkyRace.Pigeon.service.ResultIService;
import com.PigeonSkyRace.Pigeon.util.CompetitionValidator;
import com.PigeonSkyRace.Pigeon.util.CsvParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/organizer")
public class OrganizerController {

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private ResultIService resultService;

    @PostMapping("/addCompetition")
    public ResponseEntity<?> addCompetition(@RequestBody Competition competition) {
        String validationError = CompetitionValidator.validateCompetitionData(competition);
        if (validationError != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
        }

        Competition savedCompetition = competitionService.addCompetition(competition);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompetition);
    }

    @PutMapping("/{id}/addPigeonToCompetition")
    public ResponseEntity<?> updateCompetition(@PathVariable String id, @RequestParam String badge) {
        if (!StringUtils.hasText(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("competition id is required");
        }
        if (!StringUtils.hasText(badge)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("pigeon badge is required");
        }

        Optional<Result> updatedCompetition = competitionService.updateCompetition(id, badge);
        if (updatedCompetition.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedCompetition);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Competition not found");
        }
    }

    @PostMapping("/{competitionId}/displayAllCompetitionResults")
    public ResponseEntity<?> displayAllCompetitionResults(@PathVariable String competitionId) {
        List<Result> results = resultService.getCompetitionResults(competitionId);
        return ResponseEntity.status(HttpStatus.CREATED).body(results);
    }

    @PostMapping("/{competitionId}/results")
    public ResponseEntity<?> uploadRaceData(@RequestParam("file")MultipartFile file, @PathVariable String competitionId) {

        try {
            List<RaceData> raceDataList = CsvParserUtil.parseCsvFile(file);
            resultService.processRaceData(competitionId, raceDataList);
            return ResponseEntity.status(HttpStatus.OK).body(raceDataList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{competitionId}/closeCompetition")
    public ResponseEntity<?> closeCompetition(@PathVariable String competitionId) {
        Competition competition = competitionService.closeCompetition(competitionId);
        if (competition == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Competition not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(competition);
    }
}
