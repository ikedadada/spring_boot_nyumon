package com.example.shopping.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.shopping.entity.Order;
import com.example.shopping.enumeration.PaymentMethod;
import com.example.shopping.exception.StockShortageException;
import com.example.shopping.input.CartInput;
import com.example.shopping.input.CartItemInput;
import com.example.shopping.input.OrderInput;
import com.example.shopping.service.OrderService;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    OrderService orderService;

    @MockBean
    OrderSession orderSession;

    @Test
    void test_displayForm() throws Exception {
        OrderInput orderInput = new OrderInput();
        orderInput.setPaymentMethod(PaymentMethod.BANK);

        mockMvc.perform(get("/order/display-form"))
                .andExpect(status().isOk())
                .andExpect(view().name("order/orderForm"))
                .andExpect(model().attribute("orderInput", orderInput));

    }

    @Test
    void test_validateInput() throws Exception {
        OrderInput orderInput = new OrderInput();
        orderInput.setName("山田太郎");
        orderInput.setAddress("東京都港区");
        orderInput.setPhone("090-1234-5678");
        orderInput.setEmailAddress("taro@example.com");
        orderInput.setPaymentMethod(PaymentMethod.BANK);

        CartInput cartInput = new CartInput();
        cartInput.setTotalAmount(10);
        cartInput.setBillingAmount(1000);
        cartInput.setCartItemInputs(new ArrayList<CartItemInput>());

        doNothing().when(orderSession).setOrderInput(orderInput);
        doReturn(cartInput).when(orderSession).getCartInput();

        mockMvc.perform(
                post("/order/validate-input")
                        .param("name", orderInput.getName())
                        .param("address", orderInput.getAddress())
                        .param("phone", orderInput.getPhone())
                        .param("emailAddress", orderInput.getEmailAddress())
                        .param("paymentMethod", orderInput.getPaymentMethod().toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("order/orderConfirmation"))
                .andExpect(model().attribute("cartInput", cartInput));
    }

    @Test
    void test_validateInput_validate() throws Exception {
        mockMvc.perform(
                post("/order/validate-input")).andExpect(view().name("order/orderForm"))
                .andExpect(model().attributeHasFieldErrorCode("orderInput", "name", "NotBlank"))
                .andExpect(model().attributeHasFieldErrorCode("orderInput", "address", "NotBlank"))
                .andExpect(model().attributeHasFieldErrorCode("orderInput", "phone", "NotBlank"))
                .andExpect(model().attributeHasFieldErrorCode("orderInput", "emailAddress", "NotBlank"))
                .andExpect(model().attributeHasFieldErrorCode("orderInput", "paymentMethod", "NotNull"));
    }

    @Test
    void test_correctInput() throws Exception {
        OrderInput orderInput = new OrderInput();
        orderInput.setName("山田太郎");
        orderInput.setAddress("東京都港区");
        orderInput.setPhone("090-1234-5678");
        orderInput.setEmailAddress("taro@example.com");
        orderInput.setPaymentMethod(PaymentMethod.BANK);

        doReturn(orderInput).when(orderSession).getOrderInput();

        mockMvc.perform(
                post("/order/place-order")
                        .param("correct", "correct"))
                .andExpect(status().isOk())
                .andExpect(view().name("order/orderForm"))
                .andExpect(model().attribute("orderInput", orderInput));
    }

    @Test
    void test_order() throws Exception {
        OrderInput orderInput = new OrderInput();
        orderInput.setName("山田太郎");
        orderInput.setAddress("東京都港区");
        orderInput.setPhone("090-1234-5678");
        orderInput.setEmailAddress("taro@example.com");
        orderInput.setPaymentMethod(PaymentMethod.BANK);

        CartInput cartInput = new CartInput();
        cartInput.setTotalAmount(10);
        cartInput.setBillingAmount(1000);
        cartInput.setCartItemInputs(new ArrayList<CartItemInput>());

        doReturn(orderInput).when(orderSession).getOrderInput();
        doReturn(cartInput).when(orderSession).getCartInput();

        Order order = new Order();
        order.setId("o01");
        order.setCustomerName("山田太郎");
        order.setCustomerAddress("東京都港区");
        order.setCustomerPhone("090-1234-5678");
        order.setCustomerEmailAddress("taro@example.com");
        order.setPaymentMethod(PaymentMethod.BANK);
        order.setBillingAmount(1000);
        order.setOrderItems(new ArrayList<>());

        doReturn(order).when(orderService).placeOrder(orderInput, cartInput);

        mockMvc.perform(
                post("/order/place-order")
                        .param("order", "order"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/order/display-completion"))
                .andExpect(flash().attribute("order", order));
    }

    @Test
    void test_order_productEmpty() throws Exception {

        doThrow(new StockShortageException("在庫が足りません")).when(orderService).placeOrder(null, null);
        mockMvc.perform(
                post("/order/place-order")
                        .param("order", "order"))
                .andExpect(status().isOk())
                .andExpect(view().name("order/stockShortage"));
    }

}
