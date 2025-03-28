package com.gnr.ecommorce.customer.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Getter
@RequiredArgsConstructor
public class CustomerNotFoundException extends RuntimeException {
    private final String msg;
}
