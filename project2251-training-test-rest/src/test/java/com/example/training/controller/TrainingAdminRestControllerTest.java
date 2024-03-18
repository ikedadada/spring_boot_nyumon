package com.example.training.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.training.entity.Training;
import com.example.training.input.TrainingAdminInput;
import com.example.training.service.TrainingAdminService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TrainingAdminRestController.class)
public class TrainingAdminRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TrainingAdminService trainingAdminService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void test_registerTraining() throws Exception {
        Training training = new Training();
        training.setId("t99");
        doReturn(training).when(trainingAdminService).register(any());

        TrainingAdminInput trainingAdminInput = new TrainingAdminInput();
        trainingAdminInput.setTitle("SQL入門");
        trainingAdminInput.setStartDateTime(LocalDateTime.of(2021, 12, 1, 9, 30));
        trainingAdminInput.setEndDateTime(LocalDateTime.of(2021, 12, 3, 17, 0));
        trainingAdminInput.setReserved(0);
        trainingAdminInput.setCapacity(8);

        String requestBody = objectMapper.writeValueAsString(trainingAdminInput);

        mockMvc.perform(post("/api/trainings")
                .contentType(Objects.requireNonNull(MediaType.APPLICATION_JSON))
                .content(Objects.requireNonNull(requestBody)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/api/trainings/t99"));
    }

    @Test
    void test_getTraining() throws Exception {

        Training training = new Training();
        training.setId("t01");
        training.setTitle("Java研修");
        training.setStartDateTime(LocalDateTime.of(2021, 12, 1, 9, 30));
        training.setEndDateTime(LocalDateTime.of(2021, 12, 3, 17, 0));
        training.setReserved(3);
        training.setCapacity(10);
        doReturn(training).when(trainingAdminService).findById(training.getId());

        mockMvc.perform(get("/api/trainings/{id}", training.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(training.getId()))
                .andExpect(jsonPath("$.title").value(training.getTitle()))
                .andExpect(jsonPath("$.startDateTime").value("2021-12-01T09:30:00"))
                .andExpect(jsonPath("$.endDateTime").value("2021-12-03T17:00:00"))
                .andExpect(jsonPath("$.reserved").value("3"))
                .andExpect(jsonPath("$.capacity").value("10"))
                .andDo(print());
    }

    @Test
    void test_getTraining_NotFound() throws Exception {
        doReturn(null).when(trainingAdminService).findById("t99");

        mockMvc.perform(get("/api/trainings/t99").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                // .andExpect(jsonPath("$.code").value("404"))
                // .andExpect(jsonPath("$.message").value("指定されたIDのトレーニングは存在しません。id = t99"))
                .andDo(print());
    }

    @Test
    void test_getTrainings() throws Exception {
        List<Training> trainings = new ArrayList<>();
        Training training1 = new Training();
        training1.setId("t01");
        training1.setTitle("Java研修");
        training1.setStartDateTime(LocalDateTime.of(2021, 12, 1, 9, 30));
        training1.setEndDateTime(LocalDateTime.of(2021, 12, 3, 17, 0));
        training1.setReserved(3);
        training1.setCapacity(10);
        trainings.add(training1);

        Training training2 = new Training();
        training2.setId("t02");
        training2.setTitle("SQL入門");
        training2.setStartDateTime(LocalDateTime.of(2021, 12, 4, 9, 30));
        training2.setEndDateTime(LocalDateTime.of(2021, 12, 6, 17, 0));
        training2.setReserved(0);
        training2.setCapacity(8);
        trainings.add(training2);

        doReturn(trainings).when(trainingAdminService).findAll();

        mockMvc.perform(
                get("/api/trainings")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value("t01"))
                .andExpect(jsonPath("$[0].title").value("Java研修"))
                .andExpect(jsonPath("$[0].startDateTime").value("2021-12-01T09:30:00"))
                .andExpect(jsonPath("$[0].endDateTime").value("2021-12-03T17:00:00"))
                .andExpect(jsonPath("$[0].reserved").value("3"))
                .andExpect(jsonPath("$[0].capacity").value("10"))
                .andExpect(jsonPath("$[1].id").value("t02"))
                .andExpect(jsonPath("$[1].title").value("SQL入門"))
                .andExpect(jsonPath("$[1].startDateTime").value("2021-12-04T09:30:00"))
                .andExpect(jsonPath("$[1].endDateTime").value("2021-12-06T17:00:00"))
                .andExpect(jsonPath("$[1].reserved").value("0"))
                .andExpect(jsonPath("$[1].capacity").value("8"))
                .andDo(print());
    }
}
