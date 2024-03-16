package com.example.shopping.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.shopping.entity.Product;
import com.example.shopping.service.CatalogService;

@WebMvcTest(CatalogController.class)
public class CatalogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatalogService catalogService;

    @Test
    void test_displayList() throws Exception {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setId("p01");
        product.setName("消しゴム");
        product.setPrice(50);
        product.setStock(100);
        products.add(product);

        doReturn(products).when(catalogService).findAll();

        mockMvc.perform(
                get("/catalog/display-list"))
                .andExpect(status().isOk())
                .andExpect(view().name("catalog/productList"))
                .andExpect(model().attribute("productList", products));
    }

    @Test
    void test_displayDetails() throws Exception {
        Product product = new Product();
        product.setId("p01");
        product.setName("消しゴム");
        product.setPrice(50);
        product.setStock(100);

        doReturn(product).when(catalogService).findById("p01");

        mockMvc.perform(
                get("/catalog/display-details")
                        .param("productId", "p01"))
                .andExpect(status().isOk())
                .andExpect(view().name("catalog/productDetails"))
                .andExpect(model().attribute("product", product));
    }

    @Test
    void test_displayDetails_productIdIsNull() throws Exception {
        mockMvc.perform(
                get("/catalog/display-details"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void test_displayDetails_productIdIsEmpty() throws Exception {
        mockMvc.perform(
                get("/catalog/display-details")
                        .param("productId", ""))
                .andExpect(status().isBadRequest()).andDo(print());
    }

}
