package com.example.training.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import com.example.training.entity.Training;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("TrainingAdminRestControllerIntegrationTest.sql")
@Sql(value = "clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class TrainingAdminRestControllerIntegrationTestApServer {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void test_getTrainings() {
        ResponseEntity<Training[]> response = testRestTemplate.getForEntity("/api/trainings", Training[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Training[] trainings = response.getBody();
        assertThat(trainings).hasSize(3);
        Training training1 = Objects.requireNonNull(trainings)[0];
        assertThat(training1.getTitle()).isEqualTo("ビジネスマナー研修");
        Training training2 = trainings[1];
        assertThat(training2.getTitle()).isEqualTo("Java実践");
        Training training3 = trainings[2];
        assertThat(training3.getTitle()).isEqualTo("マーケティング研修");

    }

}
