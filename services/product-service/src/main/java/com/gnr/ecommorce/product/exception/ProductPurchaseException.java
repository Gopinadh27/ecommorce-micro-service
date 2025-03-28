package com.gnr.ecommorce.product.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Getter
@RequiredArgsConstructor
public class ProductPurchaseException extends RuntimeException {
   private final String msg;
}
