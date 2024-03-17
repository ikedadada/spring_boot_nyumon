package com.example.shopping.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.example.shopping.entity.Order;
import com.example.shopping.enumeration.PaymentMethod;
import com.example.shopping.input.CartInput;
import com.example.shopping.input.CartItemInput;
import com.example.shopping.input.OrderInput;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Sql("OrderControllerIntegrationTest.sql")
class OrderControllerIntegrationTest {
        @Autowired
        MockMvc mockMvc;

        @Autowired
        JdbcTemplate jdbcTemplate;

        @Test
        void test_order() throws Exception {
                OrderInput orderInput = new OrderInput();
                orderInput.setName("東京太郎");
                orderInput.setAddress("東京都");
                orderInput.setPhone("090-0000-0000");
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

                CartInput cartInput = new CartInput();
                cartInput.setCartItemInputs(cartItemInputs);

                OrderSession orderSession = new OrderSession();
                orderSession.setOrderInput(orderInput);
                orderSession.setCartInput(cartInput);

                MvcResult mvcResult = mockMvc.perform(
                                post("/order/place-order?order")
                                                .sessionAttr("scopedTarget.orderSession", orderSession))
                                .andExpect(status().is3xxRedirection())
                                .andReturn();

                Order order = (Order) mvcResult.getFlashMap().get("order");

                Map<String, Object> map = jdbcTemplate.queryForMap(
                                "SELECT * FROM t_order WHERE id = ?",
                                order.getId());

                assertThat(map).isNotNull();
                assertThat(map.get("customer_name")).isEqualTo("東京太郎");
                assertThat(map.get("customer_address")).isEqualTo("東京都");

        }

        @Test
        void test_order_at_StorgateException() throws Exception {
                OrderInput orderInput = new OrderInput();
                orderInput.setName("東京太郎");
                orderInput.setAddress("東京都");
                orderInput.setPhone("090-0000-0000");
                orderInput.setEmailAddress("taro@example.com");
                orderInput.setPaymentMethod(PaymentMethod.CONVENIENCE_STORE);

                List<CartItemInput> cartItemInputs = new ArrayList<>();

                CartItemInput keshigomu = new CartItemInput();
                keshigomu.setProductId("p01");
                keshigomu.setProductName("消しゴム");
                keshigomu.setProductPrice(100);
                keshigomu.setQuantity(100); // 在庫不足
                cartItemInputs.add(keshigomu);

                CartInput cartInput = new CartInput();
                cartInput.setCartItemInputs(cartItemInputs);

                OrderSession orderSession = new OrderSession();
                orderSession.setOrderInput(orderInput);
                orderSession.setCartInput(cartInput);

                mockMvc.perform(
                                post("/order/place-order?order")
                                                .sessionAttr("scopedTarget.orderSession", orderSession))
                                .andExpect(view().name("order/stockShortage"));

        }
}
