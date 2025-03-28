package com.gnr.ecommorce.notification.email;

import com.gnr.ecommorce.notification.kafka.order.record.Product;
import jakarta.mail.MessagingException;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.scheduling.annotation.Async;

public interface EmailService {
    @Async
    void paymentSuccessEmail(
            String toMail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) throws MessagingException;

    @Async
    void orderSuccessEmail(
            String toMail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    ) throws MessagingException;
}
