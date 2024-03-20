package com.example.training.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@Sql("TrainingAdminServiceTest.sql")
public class TrainingAdminServiceTest {

    @Autowired
    TrainingAdminService trainingAdminService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void test_delete() {
        trainingAdminService.delete("t01");
        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM training WHERE id = ?", Integer.class, "t01");

        assertThat(count).isEqualTo(0);
    }
}
