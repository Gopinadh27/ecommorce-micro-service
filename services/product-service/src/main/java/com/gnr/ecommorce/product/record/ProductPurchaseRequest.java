package com.gnr.ecommorce.product.record;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest(
    @NotNull(message = "Product is mandatory")
    Integer productId,

    @NotNull(message = "Quantity is mandatory")
    double quantity
) {
}
