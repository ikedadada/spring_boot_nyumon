package com.example.project0402shoppingstereotypeannotation.exception;

public class StockShortageException extends RuntimeException {
    public StockShortageException(String message) {
        super(message);
    }
}
