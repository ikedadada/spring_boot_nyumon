package com.example.sample.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "discount")
public class DiscountProperties {
    private double rate;
    private int max;

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getRate() {
        return rate;
    }

    public int getMax() {
        return max;
    }
}
