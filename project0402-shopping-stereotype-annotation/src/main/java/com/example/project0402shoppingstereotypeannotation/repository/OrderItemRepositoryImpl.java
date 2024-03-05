package com.example.project0402shoppingstereotypeannotation.repository;

import org.springframework.stereotype.Repository;

import com.example.project0402shoppingstereotypeannotation.entity.OrderItem;

@Repository
public class OrderItemRepositoryImpl implements OrderItemRepository {
    @Override
    public void insert(OrderItem orderItem) {
        if (orderItem == null) {
            throw new IllegalArgumentException("OrderItem is null");
        }
        System.out.println("注文商品を登録しました。id = " + orderItem.getId());
    }
}
