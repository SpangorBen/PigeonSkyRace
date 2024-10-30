package com.PigeonSkyRace.Pigeon.service.impl;

import com.PigeonSkyRace.Pigeon.model.Result;
import com.PigeonSkyRace.Pigeon.repository.ResultRepository;
import com.PigeonSkyRace.Pigeon.service.ResultIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResultServiceImpl implements ResultIService {
    @Autowired
    ResultRepository resultRepository;
    @Override
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }
}
