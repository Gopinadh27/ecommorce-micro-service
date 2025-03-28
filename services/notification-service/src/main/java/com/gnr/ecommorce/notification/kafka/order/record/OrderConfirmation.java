package com.gnr.ecommorce.notification.kafka.order.record;

import com.gnr.ecommorce.notification.kafka.payment.enums.PaymentMethod;
import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
    String orderReference,
    BigDecimal totalAmount,
    PaymentMethod paymentMethod,
    Customer customer,
    List<Product> products
) {
}
