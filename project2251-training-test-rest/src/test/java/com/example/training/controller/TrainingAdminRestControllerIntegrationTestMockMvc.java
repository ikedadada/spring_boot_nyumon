package com.example.training.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("TrainingAdminRestControllerIntegrationTest.sql")
@Transactional
public class TrainingAdminRestControllerIntegrationTestMockMvc {
    @Autowired
    MockMvc mockMvc;

    @Test
    void test_getTrainings() throws Exception {
        mockMvc.perform(
                get("/api/trainings")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].title").value("ビジネスマナー研修"))
                .andExpect(jsonPath("$[1].title").value("Java実践"));
    }

    @Test
    void test_getTraining() throws Exception {
        mockMvc.perform(
                get("/api/trainings/{id}", "t01")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("ビジネスマナー研修"));
    }

    @Test
    void test_getTraining_NotFound() throws Exception {
        mockMvc.perform(
                get("/api/trainings/{id}", "t99")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("指定されたIDのトレーニングは存在しません。id = t99"))
                .andExpect(jsonPath("$.code").value(404))
                .andDo(print());

    }
}
