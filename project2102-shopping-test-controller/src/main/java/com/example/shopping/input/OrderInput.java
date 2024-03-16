package com.example.shopping.input;

import java.io.Serializable;
import java.util.Objects;

import com.example.shopping.enumeration.PaymentMethod;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@SuppressWarnings("serial")
public class OrderInput implements Serializable {
    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    @Pattern(regexp = "0\\d{1,4}-\\d{1,4}-\\d{4}")
    private String phone;

    @NotBlank
    @Email
    private String emailAddress;

    @NotNull
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

    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OrderInput))
            return false;
        final OrderInput other = (OrderInput) o;
        // equal to all fields
        boolean isEqualName = Objects.equals(this.name, other.name);
        boolean isEqualAddress = Objects.equals(this.address, other.address);
        boolean isEqualPhone = Objects.equals(this.phone, other.phone);
        boolean isEqualEmailAddress = Objects.equals(this.emailAddress,
                other.emailAddress);
        boolean isEqualPaymentMethod = Objects.equals(this.paymentMethod,
                other.paymentMethod);
        return isEqualName && isEqualAddress && isEqualPhone && isEqualEmailAddress
                && isEqualPaymentMethod;
    }

}
