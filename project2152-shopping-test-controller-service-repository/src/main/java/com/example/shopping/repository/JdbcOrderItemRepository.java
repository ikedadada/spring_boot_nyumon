package com.example.shopping.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.shopping.entity.OrderItem;

@Repository
public class JdbcOrderItemRepository implements OrderItemRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcOrderItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(OrderItem orderItem) {
        jdbcTemplate.update(
                "INSERT INTO t_order_item (id, order_id, product_id, price_at_order, quantity) VALUES ( ?, ?, ?, ?, ?)",
                orderItem.getId(), orderItem.getOrderId(), orderItem.getProductId(), orderItem.getPriceAtOrder(),
                orderItem.getQuantity());
    }
}
