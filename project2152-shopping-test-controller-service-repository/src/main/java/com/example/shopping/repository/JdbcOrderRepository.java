package com.example.shopping.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.shopping.entity.Order;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(Order order) {
        jdbcTemplate.update(
                "INSERT INTO t_order (id, order_date_time ,customer_name, customer_address, customer_phone, customer_email_address, payment_method) VALUES (?, ?, ?, ?, ?, ?, ?)",
                order.getId(), order.getOrderDateTime(), order.getCustomerName(), order.getCustomerAddress(),
                order.getCustomerPhone(),
                order.getCustomerEmailAddress(), order.getPaymentMethod().toString());
    }
}
