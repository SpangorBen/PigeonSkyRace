package com.PigeonSkyRace.Pigeon.controller;

import com.PigeonSkyRace.Pigeon.model.Pigeon;
import com.PigeonSkyRace.Pigeon.service.PigeonService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/breeder")
public class BreederController {

    @Autowired
    private PigeonService pigeonService;

    @PostMapping("/addPigeon")
    public ResponseEntity<?> addPigeon(@RequestBody Pigeon pigeon) {
        try {
            Pigeon savedPigeon = pigeonService.addPigeon(pigeon);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPigeon);
        } catch (DuplicateKeyException e) {
            String errorMessage = "Error: The badge number must be unique.";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
        }
    }
}
