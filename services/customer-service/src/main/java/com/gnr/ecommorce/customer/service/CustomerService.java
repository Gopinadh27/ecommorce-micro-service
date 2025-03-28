package com.gnr.ecommorce.customer.service;

import com.gnr.ecommorce.customer.record.CustomerRequest;
import com.gnr.ecommorce.customer.record.CustomerResponse;
import java.util.List;

public interface CustomerService {
    String createCustomer(CustomerRequest request);

    void updateCustomer(CustomerRequest request);

    List<CustomerResponse> findAllCustomers();

    Boolean existsById(String customerId);

    CustomerResponse customerById(String customerId);

    void deleteCustomerById(String customerId);
}
