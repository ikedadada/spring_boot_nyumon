package com.example.project0502shoppingatbean.exception;

@SuppressWarnings("serial")
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String msg) {
        super(msg);
    }
}
