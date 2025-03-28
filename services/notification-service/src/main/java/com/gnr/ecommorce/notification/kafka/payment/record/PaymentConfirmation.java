package com.gnr.ecommorce.notification.kafka.payment.record;

import com.gnr.ecommorce.notification.kafka.payment.enums.PaymentMethod;
import java.math.BigDecimal;

public record PaymentConfirmation(
    String orderReference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerFirstName,
    String customerLastName,
    String customerEmail
) {
}
