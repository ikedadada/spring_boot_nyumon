package com.example.shopping.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shopping.entity.Order;
import com.example.shopping.entity.OrderItem;
import com.example.shopping.entity.Product;
import com.example.shopping.exception.StockShortageException;
import com.example.shopping.input.CartInput;
import com.example.shopping.input.CartItemInput;
import com.example.shopping.input.OrderInput;
import com.example.shopping.repository.OrderItemRepository;
import com.example.shopping.repository.OrderRepository;
import com.example.shopping.repository.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository,
            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Order placeOrder(OrderInput orderInput, CartInput cartInput) {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setOrderDateTime(LocalDateTime.now());
        order.setCustomerName(orderInput.getName());
        order.setCustomerAddress(orderInput.getAddress());
        order.setCustomerPhone(orderInput.getPhone());
        order.setCustomerEmailAddress(orderInput.getEmailAddress());
        order.setPaymentMethod(orderInput.getPaymentMethod());

        Integer billingAmount = 0;
        List<OrderItem> orderItems = new ArrayList<>();
        List<CartItemInput> cartItemInputs = cartInput.getCartItemInputs();
        for (CartItemInput cartItemInput : cartItemInputs) {
            // 在庫状況の確認と更新
            Product product = productRepository.selectById(cartItemInput.getProductId());
            if (product.getStock() < cartItemInput.getQuantity()) {
                throw new StockShortageException("在庫が不足しています");
            }
            product.setStock(product.getStock() - cartItemInput.getQuantity());
            productRepository.update(product);

            // 合計金額の計算
            billingAmount += cartItemInput.getProductPrice() * cartItemInput.getQuantity();

            // 注文明細の作成
            OrderItem orderItem = new OrderItem();
            orderItem.setId(UUID.randomUUID().toString());
            orderItem.setOrderId(order.getId());
            orderItem.setProductId(cartItemInput.getProductId());
            orderItem.setPriceAtOrder(cartItemInput.getProductPrice());
            orderItem.setQuantity(cartItemInput.getQuantity());
            orderItems.add(orderItem);
        }

        order.setBillingAmount(billingAmount);
        orderRepository.insert(order);

        for (OrderItem orderItem : orderItems) {
            orderItemRepository.insert(orderItem);
        }

        return order;
    }
}
