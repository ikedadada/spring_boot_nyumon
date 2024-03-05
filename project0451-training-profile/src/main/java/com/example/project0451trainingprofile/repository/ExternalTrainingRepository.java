package com.example.project0451trainingprofile.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.example.project0451trainingprofile.entity.Training;

@Repository
@Profile("production")
public class ExternalTrainingRepository implements TrainingRepository {
    @Override
    public List<Training> selectAll() {
        System.out.println("ExternalTrainingRepository.selectAll() called");
        List<Training> trainingList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Training training = new Training();
            training.setTitle("ex_title" + i);
            trainingList.add(training);
        }
        return trainingList;
    }
}
