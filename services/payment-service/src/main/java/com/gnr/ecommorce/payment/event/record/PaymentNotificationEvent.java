package com.gnr.ecommorce.payment.event.record;

import com.gnr.ecommorce.payment.enums.PaymentMethod;
import java.math.BigDecimal;

public record PaymentNotificationEvent(
    String orderReference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerFirstName,
    String customerLastName,
    String customerEmail
) {
}
