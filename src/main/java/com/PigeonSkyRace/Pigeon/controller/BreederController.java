package com.PigeonSkyRace.Pigeon.controller;

import com.PigeonSkyRace.Pigeon.model.Pigeon;
import com.PigeonSkyRace.Pigeon.model.Result;
import com.PigeonSkyRace.Pigeon.service.PigeonService;
import com.PigeonSkyRace.Pigeon.service.ResultIService;
import com.PigeonSkyRace.Pigeon.util.ExportResults;
import com.PigeonSkyRace.Pigeon.util.PigeonValidator;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/breeder")
public class BreederController {

    @Autowired
    private PigeonService pigeonService;
    @Autowired
    private ResultIService resultIService;

    @PostMapping("/addPigeon")
    public ResponseEntity<?> addPigeon(HttpServletRequest request, @RequestBody Pigeon pigeon) {
        try {
            String breederId = (String) request.getAttribute("breederId");
            if (breederId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized:  Missing breeder ID.");
            }
            String validationError = PigeonValidator.validatePigeonData(pigeon);
            if (validationError != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
            }

            pigeon.setBreederId(breederId);
            Pigeon savedPigeon = pigeonService.addPigeon(pigeon);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPigeon);
        } catch (DuplicateKeyException e) {
            String errorMessage = "Error: The badge number must be unique.";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
        }
    }

    @GetMapping("getAllPigeons")
    public ResponseEntity<?> getAllPigeons() {
        return ResponseEntity.status(HttpStatus.OK).body(pigeonService.getAllPigeons());
    }

    @GetMapping("allResults")
    public ResponseEntity<?> getAllResults(HttpServletRequest request) {
        try {
            String breederId = (String) request.getAttribute("breederId");
            if (breederId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized:  Missing breeder ID.");
            }
            List<Result> results = resultIService.getAllBreederResults(breederId);
            return ResponseEntity.status(HttpStatus.OK).body(results);
        } catch (DuplicateKeyException e) {
            String errorMessage = "error: missing pigeons";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
        }
    }

    @GetMapping("exportResults")
    public ResponseEntity<?> exportResults(HttpServletResponse response, HttpServletRequest request) throws DocumentException, IOException {
        String breederId = (String) request.getAttribute("breederId");
        if(breederId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized:  Missing breeder ID.");
        }

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=results" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Result> results = resultIService.getAllBreederResults(breederId);

        ExportResults exporter = new ExportResults(results);
        exporter.export(response);
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

}
