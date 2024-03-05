package com.example.project0402shoppingstereotypeannotation.repository;

import org.springframework.stereotype.Repository;

import com.example.project0402shoppingstereotypeannotation.entity.OrderItem;

@Repository
public interface OrderItemRepository {
    void insert(OrderItem orderItem);
}
