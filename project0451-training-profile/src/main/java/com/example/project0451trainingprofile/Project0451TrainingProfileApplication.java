package com.example.project0451trainingprofile;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.example.project0451trainingprofile.entity.Training;
import com.example.project0451trainingprofile.service.TrainingService;

@SpringBootApplication
@ComponentScan
public class Project0451TrainingProfileApplication {

	public static void main(String[] args) {
		System.setProperty("spring.profiles.active", "production");
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Project0451TrainingProfileApplication.class)) {
			TrainingService trainingService = context.getBean(TrainingService.class);

			List<Training> trainingList = trainingService.findAll();
			for (Training training : trainingList) {
				System.out.println(training.getTitle());
			}
		}

	}

}
