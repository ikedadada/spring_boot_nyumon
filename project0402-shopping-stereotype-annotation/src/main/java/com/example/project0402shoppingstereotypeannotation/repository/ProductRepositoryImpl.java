package com.example.project0402shoppingstereotypeannotation.repository;

import org.springframework.stereotype.Repository;

import com.example.project0402shoppingstereotypeannotation.entity.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public Product selectById(String id) {

        if ("p01".equals(id)) {
            Product product = new Product();
            product.setId("p01");
            product.setStock(10);
            return product;
        }
        if ("p02".equals(id)) {
            Product product = new Product();
            product.setId("p02");
            product.setStock(20);
            return product;
        }
        throw new IllegalArgumentException("id is invalid");
    }

    @Override
    public boolean update(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("product is null");
        }
        System.out.println("商品を更新しました。id = " + product.getId());
        return true;
    }
}
