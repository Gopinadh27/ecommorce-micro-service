package com.gnr.ecommorce.order.record;

import com.gnr.ecommorce.order.entity.PaymentMethod;
import java.math.BigDecimal;

public record OrderResponse(
    Integer id,
    String reference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerId
) {
}
