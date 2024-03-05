package com.example.project0402shoppingstereotypeannotation.entity;

import java.io.Serializable;
import java.util.List;

import com.example.project0402shoppingstereotypeannotation.enumeration.PaymentMethod;

import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class Order implements Serializable {
    private String id;
    private List<OrderItem> orderItems;
    private LocalDateTime orderDateTime;
    private Integer billingAmount;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private String customerEmailAddress;
    private PaymentMethod paymentMethod;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void setCustomerEmailAddress(String customerEmailAddress) {
        this.customerEmailAddress = customerEmailAddress;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setBillingAmount(Integer billingAmount) {
        this.billingAmount = billingAmount;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
