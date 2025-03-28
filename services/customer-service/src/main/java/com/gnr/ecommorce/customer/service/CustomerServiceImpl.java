package com.gnr.ecommorce.customer.service;

import com.gnr.ecommorce.customer.document.Customer;
import com.gnr.ecommorce.customer.exception.CustomerNotFoundException;
import com.gnr.ecommorce.customer.mapper.CustomerMapper;
import com.gnr.ecommorce.customer.record.CustomerRequest;
import com.gnr.ecommorce.customer.record.CustomerResponse;
import com.gnr.ecommorce.customer.repository.CustomerRepository;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
//import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    @Override
    public String createCustomer(CustomerRequest request) {
        var customer = customerRepository.save(customerMapper.toCustomer(request));
        return customer.getId();
    }

    @Override
    public void updateCustomer(CustomerRequest request) {
        var customer = customerRepository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                format("Can't update customer:: No customer found with the provided id::%s",request.id())
                ));
        mergeCustomer(customer,request);
        customerRepository.save(customer);
    }

    @Override
    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean existsById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    @Override
    public CustomerResponse customerById(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("No Customer found with given id:: %s",customerId)
                ));
    }

    @Override
    public void deleteCustomerById(String customerId) {
        customerRepository.deleteById(customerId);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
//        if(StringUtils.isNotBlank(request.firstName())) {
//            customer.setFirstName(request.firstName());
//        }
//        if(StringUtils.isNotBlank(request.lastName())) {
//            customer.setLastName(request.lastName());
//        }
//        if(StringUtils.isNotBlank(request.email())) {
//            customer.setEmail(request.email());
//        }
//        if(Objects.nonNull(request.address())) {
//            customer.setAddress(request.address());
//        }
    }
}
