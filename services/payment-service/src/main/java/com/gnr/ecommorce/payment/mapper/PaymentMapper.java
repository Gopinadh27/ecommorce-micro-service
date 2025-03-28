package com.gnr.ecommorce.payment.mapper;

import com.gnr.ecommorce.payment.entity.Payment;
import com.gnr.ecommorce.payment.record.PaymentRequest;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {


    public Payment toPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .id(paymentRequest.id())
                .orderId(paymentRequest.orderId())
                .amount(paymentRequest.amount())
                .paymentMethod(paymentRequest.paymentMethod())
                .build();
    }
}
