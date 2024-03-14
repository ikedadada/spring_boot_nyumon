package com.example.training;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.training.entity.Training;
import com.example.training.service.TrainingService;

@SpringBootTest
class TrainingApplicationTests {
	@Autowired
	private TrainingService service;

	@Value("${logging.level.org.springframework.jdbc.support.JdbcTransactionManager}")
	private String logLevel;

	@Test
	public void test_findById() {
		Training training = service.findById("t01");
		Assertions.assertThat(training.getTitle()).isEqualTo("ビジネスマナー研修");
		// プロパティの確認
		System.out.println("logLevel: " + logLevel);
	}

}
