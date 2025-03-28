package com.gnr.ecommorce.payment.record;

import com.gnr.ecommorce.payment.enums.PaymentMethod;
import java.math.BigDecimal;

public record PaymentRequest(
    Integer id,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    String orderReference,
    Customer customer
) {
}
