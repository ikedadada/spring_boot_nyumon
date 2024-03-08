package com.example.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.sample.service.DiscountService;

@SpringBootApplication
public class SampleApplication {

	public static void main(String[] args) {
		try (
				ConfigurableApplicationContext context = SpringApplication.run(SampleApplication.class, args)) {
			DiscountService service = context.getBean(DiscountService.class);
			int beforePrice = 10000;
			System.out.println("割引前価格：" + beforePrice);
			int finalPrice = service.calculateDiscountPrice(beforePrice);

			System.out.println("割引後価格：" + finalPrice);
		}
	}

}
