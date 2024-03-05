package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Training;
import com.example.demo.repository.TrainingRepository;

public class TrainingServiceImpl implements TrainingService {
    private final TrainingRepository trainingRepository;

    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public List<Training> findAll() {
        return trainingRepository.selectAll();
    }
}
