package com.PigeonSkyRace.Pigeon.controller;

import com.PigeonSkyRace.Pigeon.dto.AuthRequest;
import com.PigeonSkyRace.Pigeon.model.Breeder;
import com.PigeonSkyRace.Pigeon.service.BreederService;
import com.PigeonSkyRace.Pigeon.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/auth")
public class Authcontroller {

    private final Logger logger = Logger.getLogger(Authcontroller.class.getName());

    @Autowired
    private BreederService breederService;

    @PostMapping("/register")
    public ResponseEntity<Breeder> register(@RequestBody Breeder breeder) {
        Breeder createdBreeder = breederService.createBreeder(breeder);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBreeder);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) { // Use DTO
        Optional<Breeder> optionalBreeder = breederService.findByEmail(authRequest.getEmail());

        if (optionalBreeder.isPresent() &&
                PasswordUtil.hashPassword(authRequest.getPassword()).equals(optionalBreeder.get().getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
