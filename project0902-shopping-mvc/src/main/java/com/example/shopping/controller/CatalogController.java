package com.example.shopping.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shopping.service.CatalogService;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.shopping.entity.Product;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/display-list")
    public String displayList(Model model) {
        List<Product> productList = catalogService.findAll();
        model.addAttribute("productList", productList);
        return "catalog/productList";
    }

    @GetMapping("/display-details")
    // パラメータを受け取る場合は、@RequestParamを使用する
    // 例：
    public String displayDetail(@RequestParam("productId") String productId, Model model) {
        Product product = catalogService.findById(productId);
        model.addAttribute("product", product);
        return "catalog/productDetail";
    }

}
