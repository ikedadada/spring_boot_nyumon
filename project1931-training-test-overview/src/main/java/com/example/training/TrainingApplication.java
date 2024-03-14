package com.example.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.training.entity.Training;
import com.example.training.service.TrainingService;

@SpringBootApplication
public class TrainingApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TrainingApplication.class, args);
		TrainingService service = context.getBean(TrainingService.class);
		Training training = service.findById("t01");

		System.out.println("Title: " + training.getTitle());
	}

}
