package com.gnr.ecommorce.payment.kafka;

import com.gnr.ecommorce.payment.event.record.PaymentNotificationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentNotificationProducer {

    private final KafkaTemplate<String, PaymentNotificationEvent> kafkaTemplate;

    public void sendPaymentNotification(PaymentNotificationEvent paymentNotificationEvent) {
        log.info("Sending notification with body {}", paymentNotificationEvent);

        Message<PaymentNotificationEvent> message = MessageBuilder
                .withPayload(paymentNotificationEvent)
                .setHeader(KafkaHeaders.TOPIC, "payments-topic")
                .build();
        kafkaTemplate.send(message);
    }
}
