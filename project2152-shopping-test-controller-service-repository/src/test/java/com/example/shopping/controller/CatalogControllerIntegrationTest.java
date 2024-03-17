package com.example.shopping.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Sql("CatalogControllerIntegrationTest.sql")
@SuppressWarnings("null")
class CatalogControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void test_displayList() throws Exception {
        mockMvc.perform(
                get("/catalog/display-list"))
                .andExpect(content().string(containsString("消しゴム")))
                .andExpect(content().string(containsString("ノート")));

    }

    @Test
    void test_displayDetail() throws Exception {
        mockMvc.perform(
                get("/catalog/display-detail").param("id", "p01"))
                .andExpect(content().string(containsString("消しゴム")));
    }
}
