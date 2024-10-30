package com.PigeonSkyRace.Pigeon.controller;

import com.PigeonSkyRace.Pigeon.model.Pigeon;
import com.PigeonSkyRace.Pigeon.service.PigeonService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/breeder")
public class BreederController {

    @Autowired
    private PigeonService pigeonService;

    @PostMapping("/addPigeon")
    public ResponseEntity<?> addPigeon(HttpServletRequest request, @RequestBody Pigeon pigeon) {
        try {
            String breederId = (String) request.getAttribute("breederId");
            if (breederId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized:  Missing breeder ID.");
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
}
