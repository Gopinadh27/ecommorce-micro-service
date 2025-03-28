package com.gnr.ecommorce.payment.service;

import com.gnr.ecommorce.payment.event.record.PaymentNotificationEvent;
import com.gnr.ecommorce.payment.kafka.PaymentNotificationProducer;
import com.gnr.ecommorce.payment.mapper.PaymentMapper;
import com.gnr.ecommorce.payment.record.PaymentRequest;
import com.gnr.ecommorce.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements  PaymentService{

    private final PaymentRepository paymentRepository;

    private final PaymentMapper paymentMapper;

    private final PaymentNotificationProducer paymentNotificationProducer;

    @Override
    public Integer createPayment(PaymentRequest paymentRequest) {
        var payment =  paymentRepository.save(
            paymentMapper.toPayment(paymentRequest)
        );
        paymentNotificationProducer.sendPaymentNotification(
            new PaymentNotificationEvent(
                paymentRequest.orderReference(),
                paymentRequest.amount(),
                paymentRequest.paymentMethod(),
                paymentRequest.customer().firstName(),
                paymentRequest.customer().lastName(),
                paymentRequest.customer().email()
            )
        );
        return payment.getId();
    }
}
