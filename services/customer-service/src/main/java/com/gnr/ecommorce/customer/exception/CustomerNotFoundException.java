package com.gnr.ecommorce.customer.exception;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class CustomerNotFoundException extends RuntimeException {
    private final String msg;

    public CustomerNotFoundException(String msg) {
        this.msg = msg;
    }
}
