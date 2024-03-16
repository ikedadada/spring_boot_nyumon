package com.example.shopping.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.OptimisticLockingFailureException;

import com.example.shopping.entity.Product;
import com.example.shopping.input.ProductMaintenanceInput;
import com.example.shopping.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductMaintenanceServiceImplTest {
    @InjectMocks
    ProductMaintenanceServiceImpl productMaintenanceService;

    @Mock
    ProductRepository productRepository;

    @Test
    public void test_update() {
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        doReturn(true).when(productRepository).update(productCaptor.capture());

        ProductMaintenanceInput productMaintenanceInput = new ProductMaintenanceInput();
        productMaintenanceInput.setId("p01");
        productMaintenanceInput.setName("pname01");
        productMaintenanceInput.setPrice(100);
        productMaintenanceInput.setStock(10);

        productMaintenanceService.update(productMaintenanceInput);

        Product product = productCaptor.getValue();
        assertThat(product).isNotNull();
        assertThat(product.getId()).isEqualTo("p01");
        assertThat(product.getName()).isEqualTo("pname01");
        assertThat(product.getPrice()).isEqualTo(100);
        assertThat(product.getStock()).isEqualTo(10);
    }

    @Test
    public void test_update_更新に失敗() {
        doReturn(false).when(productRepository).update(any());
        ProductMaintenanceInput productMaintenanceInput = new ProductMaintenanceInput();

        assertThatThrownBy(() -> {
            productMaintenanceService.update(productMaintenanceInput);
        }).isInstanceOf(OptimisticLockingFailureException.class);
    }

    @Test
    public void test_findAll() {
        List<Product> products = new ArrayList<>();
        Product product_1 = new Product();
        product_1.setId("p01");
        product_1.setName("pname01");
        product_1.setPrice(100);
        product_1.setStock(10);
        products.add(product_1);
        Product product_2 = new Product();
        product_2.setId("p02");
        product_2.setName("pname02");
        product_2.setPrice(200);
        product_2.setStock(20);
        products.add(product_2);

        doReturn(products).when(productRepository).selectAll();

        List<Product> actual = productMaintenanceService.findAll();

        assertThat(actual.size()).isEqualTo(2);
        assertThat(actual.get(0).getId()).isEqualTo("p01");
        assertThat(actual.get(0).getName()).isEqualTo("pname01");
        assertThat(actual.get(0).getPrice()).isEqualTo(100);
        assertThat(actual.get(0).getStock()).isEqualTo(10);
        assertThat(actual.get(1).getId()).isEqualTo("p02");
        assertThat(actual.get(1).getName()).isEqualTo("pname02");
        assertThat(actual.get(1).getPrice()).isEqualTo(200);
        assertThat(actual.get(1).getStock()).isEqualTo(20);

    }

    @Test
    void test_findById() {
        Product product = new Product();
        product.setId("p01");
        product.setName("pname01");
        product.setPrice(100);
        product.setStock(10);

        doReturn(product).when(productRepository).selectById("p01");

        Product actual = productMaintenanceService.findById("p01");

        assertThat(actual.getId()).isEqualTo("p01");
        assertThat(actual.getName()).isEqualTo("pname01");
        assertThat(actual.getPrice()).isEqualTo(100);
        assertThat(actual.getStock()).isEqualTo(10);
    }

}
