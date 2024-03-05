package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class Reservation implements Serializable {
    private String id;
    private String trainingId;
    private Training training;
    private String studentTypeId;
    private StudentType studentType;
    private LocalDateTime reservedAt;
    private String name;
    private String phone;
    private String emailAddress;

}
