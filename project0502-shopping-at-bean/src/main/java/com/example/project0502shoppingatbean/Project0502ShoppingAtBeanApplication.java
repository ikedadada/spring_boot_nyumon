package com.example.project0502shoppingatbean;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.example.project0502shoppingatbean.entity.Order;
import com.example.project0502shoppingatbean.enumeration.PaymentMethod;
import com.example.project0502shoppingatbean.input.CartInput;
import com.example.project0502shoppingatbean.input.CartItemInput;
import com.example.project0502shoppingatbean.input.OrderInput;
import com.example.project0502shoppingatbean.service.OrderService;

@Configuration
@ComponentScan
public class Project0502ShoppingAtBeanApplication {

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabase dataSource = new EmbeddedDatabaseBuilder()
				.addScripts("schema.sql", "data.sql")
				.setType(EmbeddedDatabaseType.H2).build();
		return dataSource;
	}

	public static void main(String[] args) {
		try (
				ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(
						Project0502ShoppingAtBeanApplication.class);) {
			OrderService orderService = context.getBean(OrderService.class);

			OrderInput orderInput = new OrderInput();
			orderInput.setName("東京太郎");
			orderInput.setAddress("東京都");
			orderInput.setPhone("090-0000-0000");
			orderInput.setEmailAddress("taro@example.com");
			orderInput.setPaymentMethod(PaymentMethod.CONVENIENCE_STORE);

			List<CartItemInput> cartItemInputs = new ArrayList<>();

			CartItemInput keshigom = new CartItemInput();
			keshigom.setProductId("p01");
			keshigom.setProductName("消しゴム");
			keshigom.setProductPrice(100);
			keshigom.setQuantity(3);
			cartItemInputs.add(keshigom);

			CartItemInput note = new CartItemInput();
			note.setProductId("p02");
			note.setProductName("ノート");
			note.setProductPrice(200);
			note.setQuantity(4);
			cartItemInputs.add(note);

			CartInput cartInput = new CartInput();
			cartInput.setCartItemInputs(cartItemInputs);

			Order order = orderService.placeOrder(orderInput, cartInput);

			System.out.println("注文確定処理が完了しました。注文ID=" + order.getId());
		}
	}

}
