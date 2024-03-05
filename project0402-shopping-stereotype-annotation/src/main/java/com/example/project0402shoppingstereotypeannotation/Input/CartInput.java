package com.example.project0402shoppingstereotypeannotation.Input;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unused")
public class CartInput implements Serializable {
    private Integer totalAmount;
    private Integer billingAmount;
    private List<CartItemInput> cartItemInputs;

    public List<CartItemInput> getCartItemInputs() {
        return cartItemInputs;
    }

    public void setCartItemInputs(List<CartItemInput> cartItemInputs) {
        this.cartItemInputs = cartItemInputs;
    }
}
