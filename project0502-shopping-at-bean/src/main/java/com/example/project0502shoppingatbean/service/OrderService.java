package com.example.project0502shoppingatbean.service;

import com.example.project0502shoppingatbean.entity.Order;
import com.example.project0502shoppingatbean.input.CartInput;
import com.example.project0502shoppingatbean.input.OrderInput;

public interface OrderService {
    Order placeOrder(OrderInput orderInput, CartInput cartInput);
}
