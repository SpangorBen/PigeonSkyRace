package com.PigeonSkyRace.Pigeon.service.impl;

import com.PigeonSkyRace.Pigeon.model.Pigeon;
import com.PigeonSkyRace.Pigeon.model.Result;
import com.PigeonSkyRace.Pigeon.repository.ResultRepository;
import com.PigeonSkyRace.Pigeon.service.PigeonService;
import com.PigeonSkyRace.Pigeon.service.ResultIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ResultServiceImpl implements ResultIService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private PigeonService pigeonService;

    @Override
    public List<Result> getAllBreederResults(String breederId) {
        Set<String> pigeonIds = pigeonService.getAllPigeons().stream()
                .filter(p -> breederId.equals(p.getBreederId()))
                .map(Pigeon::getId)
                .collect(Collectors.toSet());

        if (pigeonIds.isEmpty()) {
            throw new IllegalArgumentException("no pigeons found for this breeder id");
        }

        return resultRepository.findAll().stream()
                .filter(result -> pigeonIds.contains(result.getPigeonId()))
                .collect(Collectors.toList());
    }
}
