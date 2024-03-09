package com.example.shopping.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shopping.entity.Product;
import com.example.shopping.input.ProductMaintenanceInput;
import com.example.shopping.repository.ProductRepository;

@Service
public class ProductMaintenanceServiceImpl implements ProductMaintenanceService {
    private final ProductRepository productRepository;

    public ProductMaintenanceServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.selectAll();
    }

    @Override
    public Product findById(String id) {
        return productRepository.selectById(id);
    }

    @Override
    public void update(ProductMaintenanceInput productMaintenanceInput) {
        Product product = productRepository.selectById(productMaintenanceInput.getId());

        product.setName(productMaintenanceInput.getName());
        product.setPrice(productMaintenanceInput.getPrice());
        product.setStock(productMaintenanceInput.getStock());

        productRepository.update(product);
    }

    @Override
    public void delete(String id) {
        productRepository.delete(id);
    }
}
