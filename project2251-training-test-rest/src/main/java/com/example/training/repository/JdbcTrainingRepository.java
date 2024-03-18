package com.example.training.repository;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.training.entity.Training;
import com.example.training.exception.DataNotFoundException;

@Repository
public class JdbcTrainingRepository implements TrainingRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTrainingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Training selectById(String id) {
        try {
            Training training = jdbcTemplate.queryForObject("SELECT * FROM training WHERE id = ?",
                    new DataClassRowMapper<>(Training.class), id);
            return training;
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("指定されたIDのトレーニングは存在しません。id = " + id);
        }
    }

    @Override
    public List<Training> selectAll() {
        return jdbcTemplate.query("SELECT * FROM training",
                new DataClassRowMapper<>(Training.class));
    }

    @Override
    public boolean update(Training training) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void insert(Training training) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public boolean delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
