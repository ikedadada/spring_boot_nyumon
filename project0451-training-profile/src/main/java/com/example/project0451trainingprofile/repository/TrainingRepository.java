package com.example.project0451trainingprofile.repository;

import java.util.List;

import com.example.project0451trainingprofile.entity.Training;

public interface TrainingRepository {
    List<Training> selectAll();
}
