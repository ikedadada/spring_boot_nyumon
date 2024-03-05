package com.example.project0451trainingprofile.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project0451trainingprofile.entity.Training;
import com.example.project0451trainingprofile.repository.TrainingRepository;

@Service
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
