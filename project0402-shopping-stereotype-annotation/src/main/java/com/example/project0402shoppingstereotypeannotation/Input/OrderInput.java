package com.example.project0402shoppingstereotypeannotation.Input;

import java.io.Serializable;
import com.example.project0402shoppingstereotypeannotation.enumeration.PaymentMethod;

@SuppressWarnings("unused")
public class OrderInput implements Serializable {
    private String name;
    private String address;
    private String phone;
    private String emailAddress;
    private PaymentMethod paymentMethod;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

}
