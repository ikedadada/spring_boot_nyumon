package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Training;
import com.example.demo.repository.TrainingRepository;

public class MockTrainingRepository implements TrainingRepository {
    @Override
    public List<Training> selectAll() {
        System.out.println("テスト用のデータをメモリ上に用意します。");
        List<Training> trainings = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Training training = new Training();
            training.setTitle("title_" + i);
            trainings.add(training);
        }
        return trainings;
    }
}
