package com.PigeonSkyRace.Pigeon.service.impl;

import com.PigeonSkyRace.Pigeon.model.Pigeon;
import com.PigeonSkyRace.Pigeon.repository.PigeonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

@Service
public class PigeonServiceImpl implements com.PigeonSkyRace.Pigeon.service.PigeonService {

    @Autowired
    private PigeonRepository pigeonRepository;

    @Override
    public Pigeon addPigeon(Pigeon pigeon) {
        int uniqueNumber = generateUniqueNumber();
        String gender = Objects.equals(pigeon.getGender(), "Male") ? "M" : "F";

        String badge = gender + uniqueNumber + "-" + pigeon.getAge();
        pigeon.setBadge(badge);
        return pigeonRepository.save(pigeon);
    }

    private int generateUniqueNumber() {
        Random random = new Random();
        return 10000 + random.nextInt(90000);
    }
}
