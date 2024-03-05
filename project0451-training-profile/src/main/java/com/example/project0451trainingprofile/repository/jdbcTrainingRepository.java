package com.example.project0451trainingprofile.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.project0451trainingprofile.entity.Training;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("staging")
public class jdbcTrainingRepository implements TrainingRepository {
    @Override
    public List<Training> selectAll() {
        System.out.println("jdbcTrainingRepository.selectAll() called");
        List<Training> trainingList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Training training = new Training();
            training.setTitle("title" + i);
            trainingList.add(training);
        }
        return trainingList;
    }
}
