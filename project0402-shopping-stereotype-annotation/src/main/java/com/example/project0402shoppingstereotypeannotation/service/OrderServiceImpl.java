package com.example.project0402shoppingstereotypeannotation.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

import com.example.project0402shoppingstereotypeannotation.Input.CartInput;
import com.example.project0402shoppingstereotypeannotation.Input.CartItemInput;
import com.example.project0402shoppingstereotypeannotation.Input.OrderInput;
import com.example.project0402shoppingstereotypeannotation.entity.Order;
import com.example.project0402shoppingstereotypeannotation.entity.OrderItem;
import com.example.project0402shoppingstereotypeannotation.entity.Product;
import com.example.project0402shoppingstereotypeannotation.exception.StockShortageException;
import com.example.project0402shoppingstereotypeannotation.repository.OrderItemRepository;
import com.example.project0402shoppingstereotypeannotation.repository.OrderRepository;
import com.example.project0402shoppingstereotypeannotation.repository.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    // コンストラクタが単一の場合は、@Autowiredアノテーションは省略可能
    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository,
            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Order placeOrder(OrderInput orderInput, CartInput cartInput) {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setCustomerName(orderInput.getName());
        order.setCustomerAddress(orderInput.getAddress());
        order.setCustomerPhone(orderInput.getPhone());
        order.setCustomerEmailAddress(orderInput.getEmailAddress());
        order.setOrderDateTime(LocalDateTime.now());
        order.setPaymentMethod(orderInput.getPaymentMethod());

        int totalAmount = calculateTotalAmount(cartInput.getCartItemInputs());
        int billingAmount = calculateTax(totalAmount);
        order.setBillingAmount(billingAmount);

        orderRepository.insert(order);

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItemInput cartItemInput : cartInput.getCartItemInputs()) {

            Product product = productRepository.selectById(cartItemInput.getProductId());
            int afterStock = product.getStock() - cartItemInput.getQuantity();

            if (afterStock < 0) {
                throw new StockShortageException("Stock is not enough");
            }

            product.setStock(afterStock);
            productRepository.update(product);

            OrderItem orderItem = new OrderItem();
            orderItem.setId(UUID.randomUUID().toString());
            orderItem.setOrderId(order.getId());
            orderItem.setProductId(cartItemInput.getProductId());
            orderItem.setPriceAtOrder(cartItemInput.getProductPrice());
            orderItem.setQuantity(cartItemInput.getQuantity());

            orderItemRepository.insert(orderItem);
            orderItems.add(orderItem);

        }

        order.setOrderItems(orderItems);

        return order;
    }

    private int calculateTotalAmount(List<CartItemInput> cartItemInputs) {
        int totalAmount = 0;
        for (CartItemInput cartItemInput : cartItemInputs) {
            totalAmount += (cartItemInput.getProductPrice() * cartItemInput.getQuantity());
        }
        return totalAmount;
    }

    private int calculateTax(int totalAmount) {
        return (int) (totalAmount * 1.1);
    }
}
