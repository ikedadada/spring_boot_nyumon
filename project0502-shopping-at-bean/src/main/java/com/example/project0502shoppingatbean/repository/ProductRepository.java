package com.example.project0502shoppingatbean.repository;

import com.example.project0502shoppingatbean.entity.Product;

public interface ProductRepository {
    Product selectById(String id);

    boolean update(Product product);
}
