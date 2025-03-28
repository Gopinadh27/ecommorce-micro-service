package com.gnr.ecommorce.product.exception;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class ProductPurchaseException extends RuntimeException {
   private final String msg;

   public ProductPurchaseException(String msg) {
      this.msg = msg;
   }
}
