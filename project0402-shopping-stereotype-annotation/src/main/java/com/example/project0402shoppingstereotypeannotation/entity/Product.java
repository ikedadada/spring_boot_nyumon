package com.example.project0402shoppingstereotypeannotation.entity;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Product implements Serializable {
    private String id;
    private String name;
    private Integer price;
    private Integer stock;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

}
