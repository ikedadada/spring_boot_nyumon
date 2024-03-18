package com.example.training.controller;

import java.io.Serializable;

@SuppressWarnings("unused")
public class ErrorBody implements Serializable {
    private int code;
    private String message;

    public ErrorBody(int httpStatusCode, String exceptionMessage) {
        this.code = httpStatusCode;
        this.message = exceptionMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
