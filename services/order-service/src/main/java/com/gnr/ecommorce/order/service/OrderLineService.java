package com.gnr.ecommorce.order.service;

import com.gnr.ecommorce.order.record.OrderLineRequest;
import com.gnr.ecommorce.order.record.OrderLineResponse;
import java.util.List;

public interface OrderLineService {

    Integer saveOrderLine(OrderLineRequest orderLineRequest);

    List<OrderLineResponse> findAllByOrderId(Integer orderId);
}
