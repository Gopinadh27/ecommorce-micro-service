package com.gnr.ecommorce.notification.document;

import com.gnr.ecommorce.notification.enums.NotificationType;
import com.gnr.ecommorce.notification.kafka.order.record.OrderConfirmation;
import com.gnr.ecommorce.notification.kafka.payment.record.PaymentConfirmation;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class Notification {

    @Id
    private String id;

    private NotificationType type;

    private LocalDateTime notificationDate;

    private OrderConfirmation orderConfirmation;

    private PaymentConfirmation paymentConfirmation;

}
