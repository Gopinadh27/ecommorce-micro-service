package com.gnr.ecommorce.order.event.record;

import com.gnr.ecommorce.order.entity.PaymentMethod;
import com.gnr.ecommorce.order.record.CustomerResponse;
import com.gnr.ecommorce.order.record.PurchaseResponse;
import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
    String orderReference,
    BigDecimal totalAmount,
    PaymentMethod paymentMethod,
    CustomerResponse customer,
    List<PurchaseResponse> products
) {
}
