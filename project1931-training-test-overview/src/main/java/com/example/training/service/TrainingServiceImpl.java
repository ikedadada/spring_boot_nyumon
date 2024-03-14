package com.example.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.training.entity.Training;
import com.example.training.repository.TrainingRepository;

@Service
@Transactional
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;

    @Value("${logging.level.org.springframework.jdbc.support.JdbcTransactionManager}")
    private String logLevel;

    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public List<Training> findAll() {
        return trainingRepository.selectAll();
    }

    @Override
    public Training findById(String id) {
        // プロパティの確認
        System.out.println("logLevel: " + logLevel);
        return trainingRepository.selectById(id);
    }

}
