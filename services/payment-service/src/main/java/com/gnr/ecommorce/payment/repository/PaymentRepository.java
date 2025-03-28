package com.gnr.ecommorce.payment.repository;

import com.gnr.ecommorce.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
