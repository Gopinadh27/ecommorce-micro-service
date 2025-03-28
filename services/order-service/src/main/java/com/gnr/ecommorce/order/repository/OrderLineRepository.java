package com.gnr.ecommorce.order.repository;

import com.gnr.ecommorce.order.entity.OrderLine;
import com.gnr.ecommorce.order.record.OrderLineResponse;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLineResponse> findAllByOrderId(Integer orderId);
}
