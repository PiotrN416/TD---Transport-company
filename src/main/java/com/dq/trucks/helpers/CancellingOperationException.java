package com.dq.trucks.helpers;

public class CancellingOperationException extends RuntimeException {

    public CancellingOperationException(String message) {
        super(message);
    }
}
