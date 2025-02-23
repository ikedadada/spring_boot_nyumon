package com.example.shopping.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.shopping.entity.Order;
import com.example.shopping.entity.OrderItem;
import com.example.shopping.entity.Product;
import com.example.shopping.enumeration.PaymentMethod;
import com.example.shopping.exception.StockShortageException;
import com.example.shopping.input.CartInput;
import com.example.shopping.input.CartItemInput;
import com.example.shopping.input.OrderInput;
import com.example.shopping.repository.OrderItemRepository;
import com.example.shopping.repository.OrderRepository;
import com.example.shopping.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @InjectMocks
    OrderServiceImpl orderService;

    @Mock
    OrderRepository orderRepository;
    @Mock
    OrderItemRepository orderItemRepository;
    @Mock
    ProductRepository productRepository;

    @Test
    void test_placeOrder() {

        Product product1 = new Product();
        product1.setId("p01");
        product1.setStock(10);
        Product product2 = new Product();
        product2.setId("p02");
        product2.setStock(5);
        doReturn(product1).when(productRepository).selectById("p01");
        doReturn(product2).when(productRepository).selectById("p02");

        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        doNothing().when(orderRepository).insert(orderCaptor.capture());

        ArgumentCaptor<OrderItem> orderItemCaptor = ArgumentCaptor.forClass(OrderItem.class);
        doNothing().when(orderItemRepository).insert(orderItemCaptor.capture());

        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        doReturn(true, true).when(productRepository).update(productCaptor.capture());

        OrderInput orderInput = new OrderInput();
        orderInput.setName("東京太郎");
        orderInput.setAddress("東京都");
        orderInput.setPhone("090-0000-0000");
        orderInput.setEmailAddress("taro@example.com");
        orderInput.setPaymentMethod(PaymentMethod.CONVENIENCE_STORE);

        List<CartItemInput> cartItemInputs = new ArrayList<>();

        CartItemInput keshigom = new CartItemInput();
        keshigom.setProductId("p01");
        keshigom.setProductName("消しゴム");
        keshigom.setProductPrice(100);
        keshigom.setQuantity(3);
        cartItemInputs.add(keshigom);

        CartItemInput note = new CartItemInput();
        note.setProductId("p02");
        note.setProductName("ノート");
        note.setProductPrice(200);
        note.setQuantity(4);
        cartItemInputs.add(note);

        CartInput cartInput = new CartInput();
        cartInput.setCartItemInputs(cartItemInputs);

        Order order = orderService.placeOrder(orderInput, cartInput);

        assertThat(order.getBillingAmount()).isEqualTo(1210);
        assertThat(order.getCustomerName()).isEqualTo("東京太郎");
        assertThat(order.getCustomerAddress()).isEqualTo("東京都");
        assertThat(order.getCustomerPhone()).isEqualTo("090-0000-0000");
        assertThat(order.getCustomerEmailAddress()).isEqualTo("taro@example.com");
        assertThat(order.getPaymentMethod()).isEqualTo(PaymentMethod.CONVENIENCE_STORE);

        List<Order> arg_orders = orderCaptor.getAllValues();
        assertThat(arg_orders).hasSize(1);
        Order arg_order = arg_orders.get(0);
        assertThat(arg_order.getBillingAmount()).isEqualTo(1210);
        assertThat(arg_order.getCustomerName()).isEqualTo("東京太郎");
        assertThat(arg_order.getCustomerAddress()).isEqualTo("東京都");
        assertThat(arg_order.getCustomerPhone()).isEqualTo("090-0000-0000");
        assertThat(arg_order.getCustomerEmailAddress()).isEqualTo("taro@example.com");
        assertThat(arg_order.getPaymentMethod()).isEqualTo(PaymentMethod.CONVENIENCE_STORE);

        List<OrderItem> arg_orderItems = orderItemCaptor.getAllValues();
        assertThat(arg_orderItems).hasSize(2);
        OrderItem arg_orderItem1 = arg_orderItems.get(0);
        assertThat(arg_orderItem1.getOrderId()).isEqualTo(order.getId());
        assertThat(arg_orderItem1.getProductId()).isEqualTo("p01");
        assertThat(arg_orderItem1.getPriceAtOrder()).isEqualTo(100);
        assertThat(arg_orderItem1.getQuantity()).isEqualTo(3);
        OrderItem arg_orderItem2 = arg_orderItems.get(1);
        assertThat(arg_orderItem2.getOrderId()).isEqualTo(order.getId());
        assertThat(arg_orderItem2.getProductId()).isEqualTo("p02");
        assertThat(arg_orderItem2.getPriceAtOrder()).isEqualTo(200);
        assertThat(arg_orderItem2.getQuantity()).isEqualTo(4);

        List<Product> arg_products = productCaptor.getAllValues();
        assertThat(arg_products).hasSize(2);
        Product arg_product1 = arg_products.get(0);
        assertThat(arg_product1.getId()).isEqualTo("p01");
        assertThat(arg_product1.getStock()).isEqualTo(7);
        Product arg_product2 = arg_products.get(1);
        assertThat(arg_product2.getId()).isEqualTo("p02");
        assertThat(arg_product2.getStock()).isEqualTo(1);

    }

    @Test
    public void test_placeOrder_在庫が足りない() {
        Product product = new Product();
        product.setStock(2);
        doReturn(product).when(productRepository).selectById("p01");

        OrderInput orderInput = new OrderInput();
        List<CartItemInput> cartItemInputs = new ArrayList<>();
        CartItemInput cartItemInput = new CartItemInput();
        cartItemInput.setProductId("p01");
        cartItemInput.setProductPrice(100);
        cartItemInput.setQuantity(3);
        cartItemInputs.add(cartItemInput);
        CartInput cartInput = new CartInput();
        cartInput.setCartItemInputs(cartItemInputs);
        assertThatThrownBy(() -> {
            orderService.placeOrder(orderInput, cartInput);
        }).isInstanceOf(StockShortageException.class);
    }
}
