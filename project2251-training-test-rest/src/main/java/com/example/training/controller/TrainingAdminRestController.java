package com.example.training.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.training.entity.Training;
import com.example.training.exception.DataNotFoundException;
import com.example.training.input.TrainingAdminInput;
import com.example.training.service.TrainingAdminService;

@RestController
@RequestMapping("/api/trainings")
public class TrainingAdminRestController {
    private final TrainingAdminService trainingAdminService;

    public TrainingAdminRestController(TrainingAdminService trainingAdminService) {
        this.trainingAdminService = trainingAdminService;
    }

    @GetMapping
    public List<Training> findAll() {
        return trainingAdminService.findAll();
    }

    @GetMapping("/{id}")
    public Training findById(@PathVariable String id) {
        Training training = trainingAdminService.findById(id);
        if (training == null) {
            throw new DataNotFoundException("指定されたIDのトレーニングは存在しません。id = " + id);
        }
        return training;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id, @Validated @RequestBody TrainingAdminInput trainingAdminInput) {
        trainingAdminInput.setId(id);
        trainingAdminService.update(trainingAdminInput);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> register(@Validated @RequestBody TrainingAdminInput trainingAdminInput) {
        Training training = trainingAdminService.register(trainingAdminInput);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(training.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        trainingAdminService.delete(id);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorBody> handleException(DataNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorBody(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }
}
