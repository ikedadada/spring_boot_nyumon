package com.example.project0402shoppingstereotypeannotation.entity;

import java.io.Serializable;

@SuppressWarnings("unused")
public class OrderItem implements Serializable {
    private String id;
    private String orderId;
    private String productId;
    private Product product;
    private Integer priceAtOrder;
    private Integer quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setPriceAtOrder(Integer priceAtOrder) {
        this.priceAtOrder = priceAtOrder;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
