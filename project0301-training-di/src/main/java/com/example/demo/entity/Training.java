package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("unused")
public class Training implements Serializable {
    private String id;
    private String title;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Integer reserved;
    private Integer capacity;
    private List<Reservation> reservations;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
