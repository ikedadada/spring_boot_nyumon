package com.example.shopping.controller;

import java.util.List;
import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.shopping.entity.Product;
import com.example.shopping.exception.DataNotFoundException;
import com.example.shopping.input.ProductMaintenanceInput;
import com.example.shopping.service.ProductMaintenanceService;

@RestController
@RequestMapping("/api/products")
public class ProductMaintenanceRestController {
    private final ProductMaintenanceService productMaintenanceService;

    public ProductMaintenanceRestController(ProductMaintenanceService productMaintenanceService) {
        this.productMaintenanceService = productMaintenanceService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productMaintenanceService.findAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id) {
        return productMaintenanceService.findById(id);
    }

    @PostMapping
    // @ResponseStatus(HttpStatus.CREATED) // ResponseEntity.created(location)
    // を使うと不要
    public ResponseEntity<Product> addProduct(@Validated @RequestBody ProductMaintenanceInput productMaintenanceInput) {
        Product product = productMaintenanceService.register(productMaintenanceInput);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(location).body(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 返り値がないので、204を返す場合ここで指定する必要がある。
    public void updateProduct(@PathVariable String id,
            @Validated @RequestBody ProductMaintenanceInput productMaintenanceInput) {
        productMaintenanceInput.setId(id);
        productMaintenanceService.update(productMaintenanceInput);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 返り値がないので、204を返す場合ここで指定する必要がある。
    public void deleteProduct(@PathVariable String id) {
        productMaintenanceService.delete(id);
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleProductNotFound(RuntimeException e) {
    }
}