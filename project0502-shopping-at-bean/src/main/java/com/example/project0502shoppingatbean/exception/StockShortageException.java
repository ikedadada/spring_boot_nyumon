package com.example.project0502shoppingatbean.exception;

@SuppressWarnings("serial")
public class StockShortageException extends RuntimeException {
    public StockShortageException(String msg) {
        super(msg);
    }
}
