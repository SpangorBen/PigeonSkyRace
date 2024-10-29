package com.PigeonSkyRace.Pigeon.service;

import com.PigeonSkyRace.Pigeon.model.Pigeon;
import org.springframework.stereotype.Service;

@Service
public interface PigeonInterface {
    Pigeon addPigeon(Pigeon pigeon);
}
