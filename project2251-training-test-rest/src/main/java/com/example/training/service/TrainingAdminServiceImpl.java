package com.example.training.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.training.entity.Training;
import com.example.training.input.TrainingAdminInput;
import com.example.training.repository.TrainingRepository;

@Service
public class TrainingAdminServiceImpl implements TrainingAdminService {

    private final TrainingRepository trainingRepository;

    public TrainingAdminServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public List<Training> findAll() {
        return trainingRepository.selectAll();
    }

    @Override
    public Training findById(String id) {
        return trainingRepository.selectById(id);
    }

    @Override
    public void update(TrainingAdminInput trainingAdminInput) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Training register(TrainingAdminInput trainingAdminInput) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

}
