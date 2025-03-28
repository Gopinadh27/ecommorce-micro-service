package com.gnr.ecommorce.order.record;

public record CustomerResponse(
    String id,
    String firstName,
    String lastName,
    String email
) {
}
