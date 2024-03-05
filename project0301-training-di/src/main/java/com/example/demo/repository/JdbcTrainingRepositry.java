package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Training;

public class JdbcTrainingRepositry implements TrainingRepository {

    @Override
    public List<Training> selectAll() {
        System.out.println("データベースからデータを取得します");
        List<Training> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Training training = new Training();
            training.setTitle("title_" + i);
            list.add(training);
        }
        return list;
    }
}
