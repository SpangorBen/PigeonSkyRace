package com.PigeonSkyRace.Pigeon.service;

import com.PigeonSkyRace.Pigeon.model.Breeder;

import java.util.Optional;

public interface BreederService {
    Breeder createBreeder(Breeder breeder);
    Optional<Breeder> findByEmail(String email);
}
