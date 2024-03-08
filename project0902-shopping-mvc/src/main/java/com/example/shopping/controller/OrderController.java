package com.example.shopping.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.shopping.entity.Order;
import com.example.shopping.enumeration.PaymentMethod;
import com.example.shopping.exception.StockShortageException;
import com.example.shopping.input.CartInput;
import com.example.shopping.input.CartItemInput;
import com.example.shopping.input.OrderInput;
import com.example.shopping.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // ハンズオンの都合上、カートの中身は固定値とする
    private CartInput dummyCartInput() {
        List<CartItemInput> cartItemInputs = new ArrayList<>();

        CartItemInput keshigom = new CartItemInput();
        keshigom.setProductId("p01");
        keshigom.setProductName("消しゴム");
        keshigom.setProductPrice(100);
        keshigom.setQuantity(4);
        cartItemInputs.add(keshigom);

        CartItemInput note = new CartItemInput();
        note.setProductId("p02");
        note.setProductName("ノート");
        note.setProductPrice(200);
        note.setQuantity(5);
        cartItemInputs.add(note);

        CartInput cartInput = new CartInput();
        cartInput.setCartItemInputs(cartItemInputs);
        int totalAmount = cartItemInputs.stream().mapToInt(c -> c.getProductPrice() * c.getQuantity()).sum();
        cartInput.setTotalAmount(totalAmount);
        int billingAmount = (int) (totalAmount * 1.1);
        cartInput.setBillingAmount(billingAmount);
        return cartInput;
    }

    @GetMapping("/display-form")
    public String displayName(Model model) {
        OrderInput orderInput = new OrderInput();
        orderInput.setPaymentMethod(PaymentMethod.BANK);
        model.addAttribute("orderInput", orderInput);
        return "order/orderForm";
    }

    @PostMapping("/validate-input")
    public String validateInput(
            @Validated OrderInput orderInput, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("orderInput", orderInput);
            return "order/orderForm";
        }
        CartInput cartInput = dummyCartInput();
        model.addAttribute("cartInput", cartInput);
        return "order/orderConfirmation";
    }

    @PostMapping(value = "/place-order", params = "back")
    public String correctInput(@Validated OrderInput orderInput, Model model) {
        return "order/orderForm";
    }

    @PostMapping(value = "/place-order", params = "confirm")
    public String order(@Validated OrderInput orderInput, Model model) {
        CartInput cartInput = dummyCartInput();
        Order order = orderService.placeOrder(orderInput, cartInput);
        model.addAttribute("order", order);
        return "order/orderCompletion";
    }

    @ExceptionHandler(StockShortageException.class)
    public String displayStockShortagePage() {
        return "exception/stockShortage";
    }

}
