package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Training;

public interface TrainingRepository {

    List<Training> selectAll();
}
