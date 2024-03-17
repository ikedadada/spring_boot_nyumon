package com.example.shopping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.shopping.entity.Order;
import com.example.shopping.exception.StockShortageException;
import com.example.shopping.input.OrderInput;
import com.example.shopping.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final OrderSession orderSession;
    private final OrderService orderService;
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderSession orderSession, OrderService orderService) {
        this.orderSession = orderSession;
        this.orderService = orderService;
    }

    @GetMapping("/display-form")
    public String displayForm(Model model) {
        return "order/orderForm";
    }

    @PostMapping("/validate-input")
    public String validateInput(@Validated OrderInput orderInput, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "order/orderForm";
        }
        orderSession.setOrderInput(orderInput);
        return "redirect:/order/confirm";
    }

    @PostMapping(value = "/place-order", params = "correct")
    public String correctInput(Model model) {
        return "order/orderForm";
    }

    @PostMapping(value = "/place-order", params = "order")
    public String order(RedirectAttributes redirectAttributes) {
        Order order = orderService.placeOrder(orderSession.getOrderInput(), orderSession.getCartInput());
        redirectAttributes.addFlashAttribute("order", order);
        return "redirect:/order/display-completion";
    }

    @GetMapping("/display-completion")
    public String displayCompletion(Model model) {
        return "order/orderCompletion";
    }

    @ExceptionHandler(StockShortageException.class)
    public String displayStockShortagePage() {
        return "order/stockShortage";
    }

    @ExceptionHandler(Exception.class)
    public void displayErrorPage(Exception e) {
        logger.error("エラーが発生しました", e);
    }
}
