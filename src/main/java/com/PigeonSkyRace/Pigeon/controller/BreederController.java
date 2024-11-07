package com.PigeonSkyRace.Pigeon.controller;

import com.PigeonSkyRace.Pigeon.model.Pigeon;
import com.PigeonSkyRace.Pigeon.model.Result;
import com.PigeonSkyRace.Pigeon.service.PigeonService;
import com.PigeonSkyRace.Pigeon.service.ResultIService;
import com.PigeonSkyRace.Pigeon.util.ExportResults;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/breeder")
public class BreederController {

    @Autowired
    private PigeonService pigeonService;
    @Autowired
    private ResultIService resultIService;

    @PostMapping("/addPigeon")
    public ResponseEntity<?> addPigeon(HttpServletRequest request,@Valid @RequestBody Pigeon pigeon, BindingResult result) {
        try {
            String breederId = (String) request.getAttribute("breederId");
            if (breederId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized:  Missing breeder ID.");
            }

            if (result.hasErrors()) {
                List<String> errors = result.getFieldErrors().stream()
                        .map(error -> error.getField() + ": " + error.getDefaultMessage())
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errors);
            }

            pigeon.setBreederId(breederId);
            Pigeon savedPigeon = pigeonService.addPigeon(pigeon);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPigeon);
        } catch (DuplicateKeyException e) {
            String errorMessage = "Error: The badge number must be unique.";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
        }
    }


    @GetMapping("/getAllPigeons")
    public ResponseEntity<List<Pigeon>> getAllPigeons() {
        List<Pigeon> pigeons = pigeonService.getAllPigeons();
        return ResponseEntity.status(HttpStatus.OK).body(pigeons);
    }

    @GetMapping("/allResults")
    public ResponseEntity<?> getAllResults(HttpServletRequest request) {
        String breederId = (String) request.getAttribute("breederId");
        if (breederId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Missing breeder ID.");
        }

        List<Result> results = resultIService.getAllBreederResults(breederId);
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @GetMapping("/exportResults")
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
