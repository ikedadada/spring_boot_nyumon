package com.example.demo;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.demo.entity.Training;
import com.example.demo.repository.TrainingRepository;
import com.example.demo.service.TrainingService;
import com.example.demo.service.TrainingServiceImpl;

public class TrainingServiceImplTest {
    @Test
    public void testFindAll() {
        TrainingRepository trainingRepository = new MockTrainingRepository();
        TrainingService trainingService = new TrainingServiceImpl(trainingRepository);
        List<Training> trainings = trainingService.findAll();

        Assertions.assertThat(trainings.size()).isEqualTo(5);

    }
}
