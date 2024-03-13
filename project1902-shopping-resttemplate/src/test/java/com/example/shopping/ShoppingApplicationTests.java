package com.example.shopping;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import com.example.shopping.entity.Product;
import com.example.shopping.input.ProductMaintenanceInput;

class ShoppingApplicationTests {

	@Test
	public void test() {
		RestTemplate restTemplate = new RestTemplateBuilder().rootUri("http://localhost:8080").build();
		// Post
		ProductMaintenanceInput productMaintenanceInput = new ProductMaintenanceInput();
		productMaintenanceInput.setId("P0001");
		productMaintenanceInput.setName("商品1");
		productMaintenanceInput.setPrice(100);
		productMaintenanceInput.setStock(10);
		URI uri = restTemplate.postForLocation("/api/products", productMaintenanceInput);

		System.out.println(uri);

		RestTemplate restTemplate2 = new RestTemplate();
		// Get
		Product result = restTemplate2.getForObject(uri, Product.class);
		System.out.println(result);

		// Put
		productMaintenanceInput.setPrice(200);
		restTemplate.put(uri, productMaintenanceInput);

		// Get
		result = restTemplate2.getForObject(uri, Product.class);
		System.out.println(result);

		// Delete
		restTemplate.delete(uri);

	}

}
