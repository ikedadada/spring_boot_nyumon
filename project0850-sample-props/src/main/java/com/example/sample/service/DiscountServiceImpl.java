package com.example.sample.service;

import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountProperties properties;

    public DiscountServiceImpl(DiscountProperties properties) {
        this.properties = properties;
    }

    @Override
    public int calculateDiscountPrice(int originalPrice) {
        double discountRate = properties.getRate();
        int discountMax = properties.getMax();

        System.out.println("ディスカウント率： " + discountRate);
        System.out.println("ディスカウント上限： " + discountMax);

        int discount = (int) (originalPrice * discountRate);

        if (discount > discountMax) {
            System.out.println("ディスカウント額が上限値を超えました。discount = " + discount + ", discountMax = " + discountMax);
            return originalPrice - discountMax;
        }
        return originalPrice - discount;
    }
}
