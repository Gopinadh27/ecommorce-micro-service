package com.gnr.ecommorce.customer.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
