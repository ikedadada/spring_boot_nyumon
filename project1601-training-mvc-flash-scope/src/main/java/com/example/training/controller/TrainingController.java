package com.example.training.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.training.entity.Training;
import com.example.training.service.TrainingService;

@Controller
@RequestMapping("/training")
public class TrainingController {
    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping("/display-list")
    public String displayList(Model model) {
        List<Training> trainings = trainingService.findAll();
        model.addAttribute("trainings", trainings);
        return "training/trainingList";
    }

    @GetMapping("/display-details")
    public String displayDetail(@RequestParam String trainingId, Model model) {
        Training training = trainingService.findById(trainingId);
        model.addAttribute("training", training);
        return "training/trainingDetails";
    }
}
