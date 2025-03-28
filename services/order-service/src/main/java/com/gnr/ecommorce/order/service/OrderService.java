package com.gnr.ecommorce.order.service;

import com.gnr.ecommorce.order.record.OrderRequest;
import com.gnr.ecommorce.order.record.OrderResponse;
import java.util.List;

public interface OrderService {
    Integer createOrder(OrderRequest orderRequest);

    List<OrderResponse> findAllOrders();

    OrderResponse findOrderById(Integer orderId);
}
