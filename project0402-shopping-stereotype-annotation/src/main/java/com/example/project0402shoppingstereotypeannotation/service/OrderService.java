package com.example.project0402shoppingstereotypeannotation.service;

import com.example.project0402shoppingstereotypeannotation.Input.CartInput;
import com.example.project0402shoppingstereotypeannotation.Input.OrderInput;
import com.example.project0402shoppingstereotypeannotation.entity.Order;

public interface OrderService {
    Order placeOrder(OrderInput orderInput, CartInput cartInput);
}
