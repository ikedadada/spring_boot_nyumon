package com.example.shopping.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import com.example.shopping.entity.Product;

@JdbcTest
@Sql("JdbcProductRepositoryTest.sql")
public class JdbcProductRepositoryTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JdbcProductRepository jdbcProductRepository;

    @BeforeEach
    void setUp() {
        jdbcProductRepository = new JdbcProductRepository(jdbcTemplate);
    }

    @Test
    void testSelectById() {
        Product product = jdbcProductRepository.selectById("p01");
        assertNotNull(product);
        assertEquals("p01", product.getId());
        assertEquals("消しゴム", product.getName());
        assertEquals(100, product.getPrice());
        assertEquals(10, product.getStock());
    }

    @Test
    void testSelectAll() {
        List<Product> products = jdbcProductRepository.selectAll();
        assertEquals(5, products.size());
    }

    @Test
    void testUpdate() {
        Product product = new Product();
        product.setId("p01");
        product.setName("消しゴム改");
        product.setPrice(120);
        product.setStock(10);
        boolean updated = jdbcProductRepository.update(product);
        assertEquals(true, updated);

        Map<String, Object> map = jdbcTemplate.queryForMap("SELECT * FROM t_product WHERE id = 'p01'");
        assertEquals("p01", map.get("id"));
        assertEquals("消しゴム改", map.get("name"));
        assertEquals(120, map.get("price"));
        assertEquals(10, map.get("stock"));
    }

}
