package com.example.project0402shoppingstereotypeannotation.Input;

import java.io.Serializable;

@SuppressWarnings("unused")
public class CartItemInput implements Serializable {
    private String id;
    private String productId;
    private String productName;
    private Integer productPrice;
    private Integer quantity;

    public Integer getProductPrice() {
        return productPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
