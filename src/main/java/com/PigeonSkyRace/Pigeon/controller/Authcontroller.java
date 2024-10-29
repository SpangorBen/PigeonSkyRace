package com.PigeonSkyRace.Pigeon.controller;

import com.PigeonSkyRace.Pigeon.dto.AuthRequest;
import com.PigeonSkyRace.Pigeon.model.Breeder;
import com.PigeonSkyRace.Pigeon.service.BreederService;
import com.PigeonSkyRace.Pigeon.util.PasswordUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/auth")
public class Authcontroller {

    private final Logger logger = Logger.getLogger(Authcontroller.class.getName());

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration.time}")
    private long jwtExpirationTime;

    @Autowired
    private BreederService breederService;

    @PostMapping("/register")
    public ResponseEntity<Breeder> register(@RequestBody Breeder breeder) {
        Breeder createdBreeder = breederService.createBreeder(breeder);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBreeder);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        Optional<Breeder> optionalBreeder = breederService.findByEmail(authRequest.getEmail());

        if (optionalBreeder.isPresent() &&
                PasswordUtil.hashPassword(authRequest.getPassword()).equals(optionalBreeder.get().getPassword())) {
            Breeder breeder = optionalBreeder.get();

            String token = JWT.create()
                    .withSubject(breeder.getEmail())
                    .withClaim("breederId", breeder.getId())
                    .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpirationTime))
                    .sign(Algorithm.HMAC512(jwtSecret));

            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
