package com.example.shopping.input;

import java.io.Serializable;

import com.example.shopping.enumeration.PaymentMethod;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@SuppressWarnings("serial")
public class OrderInput implements Serializable {
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @Pattern(regexp = "^(0[1-9]\\d{8}|0[789]0\\d{8})$")
    private String phone;

    @Email
    @NotBlank
    private String emailAddress;

    private PaymentMethod paymentMethod;

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
