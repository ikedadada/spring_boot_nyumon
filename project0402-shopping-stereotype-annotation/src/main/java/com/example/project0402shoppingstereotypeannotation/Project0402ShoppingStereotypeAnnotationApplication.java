package com.example.project0402shoppingstereotypeannotation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.example.project0402shoppingstereotypeannotation.Input.CartInput;
import com.example.project0402shoppingstereotypeannotation.Input.CartItemInput;
import com.example.project0402shoppingstereotypeannotation.Input.OrderInput;
import com.example.project0402shoppingstereotypeannotation.entity.Order;
import com.example.project0402shoppingstereotypeannotation.enumeration.PaymentMethod;
import com.example.project0402shoppingstereotypeannotation.service.OrderService;

// @Beansメソッドを利用するような場合、@Configurationアノテーションを付与する
@ComponentScan
public class Project0402ShoppingStereotypeAnnotationApplication {

	public static void main(String[] args) {
		try (ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(
				Project0402ShoppingStereotypeAnnotationApplication.class)) {
			OrderService orderService = context.getBean(OrderService.class);

			OrderInput orderInput = new OrderInput();
			orderInput.setName("Taro Yamada");
			orderInput.setAddress("Tokyo");
			orderInput.setEmailAddress("taro@example.com");
			orderInput.setPaymentMethod(PaymentMethod.CONVENIENCE_STORE);

			List<CartItemInput> cartItemInputs = new ArrayList<>();

			CartItemInput keshigomu = new CartItemInput();
			keshigomu.setProductId("p01");
			keshigomu.setProductName("消しゴム");
			keshigomu.setProductPrice(100);
			keshigomu.setQuantity(3);
			cartItemInputs.add(keshigomu);

			CartItemInput note = new CartItemInput();
			note.setProductId("p02");
			note.setProductName("ノート");
			note.setProductPrice(200);
			note.setQuantity(4);
			cartItemInputs.add(note);

			CartInput cartInput = new CartInput();
			cartInput.setCartItemInputs(cartItemInputs);

			Order order = orderService.placeOrder(orderInput, cartInput);

			System.out.println("注文確定処理が完了しました。 注文ID=" + order.getId());

		}
	}

}
