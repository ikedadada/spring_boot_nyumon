package com.example.training.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.training.entity.Reservation;
import com.example.training.entity.StudentType;
import com.example.training.entity.Training;
import com.example.training.exception.CapacityOverException;
import com.example.training.input.ReservationInput;
import com.example.training.repository.ReservationRepository;
import com.example.training.repository.StudentTypeRepository;
import com.example.training.repository.TrainingRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final StudentTypeRepository studentTypeRepository;
    private final TrainingRepository trainingRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
            StudentTypeRepository studentTypeRepository,
            TrainingRepository trainingRepository) {
        this.reservationRepository = reservationRepository;
        this.studentTypeRepository = studentTypeRepository;
        this.trainingRepository = trainingRepository;
    }

    @Override
    public List<StudentType> findAllStudentType() {
        return studentTypeRepository.selectAll();
    }

    @Override
    public StudentType findStudentTypeByCode(String studentTypeCode) {
        return studentTypeRepository.selectByCode(studentTypeCode);
    }

    @Transactional
    @Override
    public Reservation reserve(ReservationInput reservationInput) {
        Training training = trainingRepository.selectById(reservationInput.getTrainingId());
        training.setReserved(training.getReserved() + 1);
        if (training.getReserved() > training.getCapacity()) {
            throw new CapacityOverException("定員オーバー");
        }
        trainingRepository.update(training);

        StudentType studentType = studentTypeRepository.selectByCode(reservationInput.getStudentTypeCode());

        Reservation reservation = new Reservation();
        reservation.setId(UUID.randomUUID().toString());
        reservation.setTrainingId(reservationInput.getTrainingId());
        reservation.setStudentTypeId(studentType.getId());
        reservation.setName(reservationInput.getName());
        reservation.setPhone(reservationInput.getPhone());
        reservation.setReservedDateTime(LocalDateTime.now());
        reservation.setEmailAddress(reservationInput.getEmailAddress());
        reservationRepository.insert(reservation);

        return reservation;
    }

}
