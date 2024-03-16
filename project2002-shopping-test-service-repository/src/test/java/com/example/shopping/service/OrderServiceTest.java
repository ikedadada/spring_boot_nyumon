package com.example.shopping.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.example.shopping.entity.Order;
import com.example.shopping.entity.OrderItem;
import com.example.shopping.entity.Product;
import com.example.shopping.enumeration.PaymentMethod;
import com.example.shopping.input.CartInput;
import com.example.shopping.input.CartItemInput;
import com.example.shopping.input.OrderInput;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@Sql("OrderServiceTest.sql")
class OrderServiceTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    OrderService orderService;

    @Test
    void test_placeOrder() {
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

        Order dbOrder = jdbcTemplate.queryForObject(
                "SELECT * FROM t_order WHERE id = ?", new DataClassRowMapper<>(Order.class), order.getId());
        assertThat(dbOrder).isNotNull();
        assertThat(dbOrder.getId()).isEqualTo(order.getId());
        assertThat(dbOrder.getCustomerName()).isEqualTo("東京太郎");
        assertThat(dbOrder.getCustomerAddress()).isEqualTo("東京都");

        List<OrderItem> dbOrderItems = jdbcTemplate.query(
                "SELECT * FROM t_order_item WHERE order_id = ?", new DataClassRowMapper<>(OrderItem.class),
                order.getId());
        assertThat(dbOrderItems).hasSize(2);

        Product keshigomProduct = jdbcTemplate.queryForObject(
                "SELECT * FROM t_product WHERE id = ?", new DataClassRowMapper<>(Product.class), "p01");
        assertThat(keshigomProduct.getStock()).isEqualTo(7);

        Product noteProduct = jdbcTemplate.queryForObject(
                "SELECT * FROM t_product WHERE id = ?", new DataClassRowMapper<>(Product.class), "p02");
        assertThat(noteProduct.getStock()).isEqualTo(16);
    }
}
