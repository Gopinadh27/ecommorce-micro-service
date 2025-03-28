package com.gnr.ecommorce.order.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException {
    private final String msg;
}
