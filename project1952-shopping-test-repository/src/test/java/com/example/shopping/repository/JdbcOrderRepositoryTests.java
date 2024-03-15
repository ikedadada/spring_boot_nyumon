package com.example.shopping.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.shopping.entity.Order;
import com.example.shopping.enumeration.PaymentMethod;

@JdbcTest
public class JdbcOrderRepositoryTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JdbcOrderRepository jdbcOrderRepository;

    @BeforeEach
    void setUp() {
        jdbcOrderRepository = new JdbcOrderRepository(jdbcTemplate);
    }

    @Test
    void testInsert() {
        Order order = new Order();
        order.setId("o01");
        order.setOrderDateTime(LocalDateTime.of(2021, 1, 1, 10, 0, 0));
        order.setBillingAmount(1000);
        order.setCustomerName("山田太郎");
        order.setCustomerAddress("東京都千代田区");
        order.setCustomerPhone("03-1234-5678");
        order.setCustomerEmailAddress("yamada@example.com");
        order.setPaymentMethod(PaymentMethod.CONVENIENCE_STORE);
        jdbcOrderRepository.insert(order);

        Map<String, Object> map = jdbcTemplate.queryForMap("SELECT * FROM t_order WHERE id = 'o01'");

        assertThat(map.get("id")).isEqualTo("o01");
        assertThat(map.get("order_date_time"))
                .isInstanceOf(Timestamp.class)
                .extracting(timestamp -> ((Timestamp) timestamp).toLocalDateTime())
                .isEqualTo(LocalDateTime.of(2021, 1, 1, 10, 0, 0));
        assertThat(map.get("billing_amount")).isInstanceOf(Integer.class).isEqualTo(1000);
        assertThat(map.get("customer_name")).isEqualTo("山田太郎");
        assertThat(map.get("customer_address")).isEqualTo("東京都千代田区");
        assertThat(map.get("customer_phone")).isEqualTo("03-1234-5678");
        assertThat(map.get("customer_email_address")).isEqualTo("yamada@example.com");
        assertThat(map.get("payment_method")).isEqualTo(PaymentMethod.CONVENIENCE_STORE.toString());

    }
}
