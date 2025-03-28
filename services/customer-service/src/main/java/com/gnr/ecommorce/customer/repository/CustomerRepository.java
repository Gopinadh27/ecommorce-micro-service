package com.gnr.ecommorce.customer.repository;

import com.gnr.ecommorce.customer.document.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
