package com.gnr.ecommorce.customer.mapper;

import com.gnr.ecommorce.customer.document.Customer;
import com.gnr.ecommorce.customer.record.CustomerRequest;
import com.gnr.ecommorce.customer.record.CustomerResponse;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest request) {
        if(Objects.isNull(request)) {
            return null;
        }
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
        customer.getId(),
        customer.getFirstName(),
        customer.getLastName(),
        customer.getEmail(),
        customer.getAddress()
        );
    }
}
