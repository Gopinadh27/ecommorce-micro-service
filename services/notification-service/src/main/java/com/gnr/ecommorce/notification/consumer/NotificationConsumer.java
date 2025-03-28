package com.gnr.ecommorce.notification.consumer;

import com.gnr.ecommorce.notification.document.Notification;
import com.gnr.ecommorce.notification.email.EmailService;
import com.gnr.ecommorce.notification.enums.NotificationType;
import com.gnr.ecommorce.notification.kafka.order.record.OrderConfirmation;
import com.gnr.ecommorce.notification.kafka.payment.record.PaymentConfirmation;
import com.gnr.ecommorce.notification.repository.NotificationRepository;
import jakarta.mail.MessagingException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;

    private final EmailService emailService;

    @KafkaListener(topics = "payments-topic")
    public void consumePaymentNotification(
            PaymentConfirmation paymentConfirmation
    ) throws MessagingException {
        log.info(String.format("Consuming the message from %s Topic::%s"
                , "payments-topic"
                , paymentConfirmation));

        notificationRepository.save(Notification.builder()
                .type(NotificationType.PAYMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .paymentConfirmation(paymentConfirmation)
                .build());

        //todo send email
        var customerName = paymentConfirmation.customerFirstName()+" "+paymentConfirmation.customerLastName();
        emailService.paymentSuccessEmail(
            paymentConfirmation.customerEmail(),
            customerName,
            paymentConfirmation.amount(),
            paymentConfirmation.orderReference()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderNotification(
            OrderConfirmation orderConfirmation
    ) throws MessagingException {
        log.info(String.format("Consuming the message from %s Topic::%s"
                , "order-topic"
                , orderConfirmation));

        notificationRepository.save(Notification.builder()
                .type(NotificationType.ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build());

        //todo send email
        var customerName = orderConfirmation.customer().firstName()+" "+orderConfirmation.customer().lastName();
        emailService.orderSuccessEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }
}


