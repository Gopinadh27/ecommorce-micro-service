package com.gnr.ecommorce.payment.service;

import com.gnr.ecommorce.payment.record.PaymentRequest;

public interface PaymentService {
    Integer createPayment(PaymentRequest paymentRequest);
}
