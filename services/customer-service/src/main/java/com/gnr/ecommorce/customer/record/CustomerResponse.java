package com.gnr.ecommorce.customer.record;

import com.gnr.ecommorce.customer.document.Address;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
