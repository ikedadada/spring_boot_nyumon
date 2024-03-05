package com.example.demo;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Training;
import com.example.demo.repository.JdbcTrainingRepositry;
import com.example.demo.repository.TrainingRepository;
import com.example.demo.service.TrainingService;
import com.example.demo.service.TrainingServiceImpl;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		TrainingRepository trainingRepository = new JdbcTrainingRepositry();
		TrainingService trainingService = new TrainingServiceImpl(trainingRepository);

		List<Training> list = trainingService.findAll();
		for (Training training : list) {
			System.out.println(training.getTitle());
		}
	}

}
