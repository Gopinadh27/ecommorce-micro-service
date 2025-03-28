package com.gnr.ecommorce.notification.kafka.order.record;

public record Customer(
        String id,

        String firstName,

        String lastName,

        String email
) {
}
