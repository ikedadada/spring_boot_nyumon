package com.example.project0402shoppingstereotypeannotation.repository;

import org.springframework.stereotype.Repository;

import com.example.project0402shoppingstereotypeannotation.entity.Order;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public void insert(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order is null");
        }
        System.out.println("注文を登録しました。id = " + order.getId());
    }
}