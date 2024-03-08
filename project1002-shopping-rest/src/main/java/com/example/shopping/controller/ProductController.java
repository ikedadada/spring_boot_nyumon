package com.example.shopping.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.shopping.service.ProductMaintenanceService;
import com.example.shopping.entity.Product;

@Controller
public class ProductController {
    private final ProductMaintenanceService productMaintenanceService;

    public ProductController(ProductMaintenanceService productMaintenanceService) {
        this.productMaintenanceService = productMaintenanceService;
    }

    @GetMapping("/products")
    @ResponseBody
    public List<Product> listProducts() {
        return productMaintenanceService.findAll();
    }

    @GetMapping("/products/{id}")
    @ResponseBody
    public Product getProduct(@PathVariable String id) {
        return productMaintenanceService.findById(id);
    }
}
