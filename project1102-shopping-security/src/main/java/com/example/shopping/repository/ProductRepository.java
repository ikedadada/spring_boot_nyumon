package com.example.shopping.repository;

import java.util.List;

import com.example.shopping.entity.Product;
import com.example.shopping.exception.DataNotFoundException;

public interface ProductRepository {
    Product selectById(String id) throws DataNotFoundException;

    List<Product> selectAll();

    boolean update(Product product) throws DataNotFoundException;

    boolean delete(String id) throws DataNotFoundException;

}
