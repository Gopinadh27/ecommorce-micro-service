package com.gnr.ecommorce.order.repository;

import com.gnr.ecommorce.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
