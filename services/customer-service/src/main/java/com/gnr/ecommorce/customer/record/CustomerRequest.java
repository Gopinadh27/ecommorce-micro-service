package com.gnr.ecommorce.customer.record;

import com.gnr.ecommorce.customer.document.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
         String id,
         @NotNull(message = "Customer firstName is required")
         @NotBlank(message = "Customer firstName is required")
         String firstName,
         @NotNull(message = "Customer lastName is required")
         @NotBlank(message = "Customer lastName is required")
         String lastName,
         @NotNull(message = "Customer email is required")
         @Email(message = "Customer email is not a valid email address")
         String email,
         @NotNull(message = "Address is required")
         Address address
) {
}
