package com.example.project0402shoppingstereotypeannotation.repository;

import com.example.project0402shoppingstereotypeannotation.entity.Product;

public interface ProductRepository {
    Product selectById(String id);

    boolean update(Product product);

}