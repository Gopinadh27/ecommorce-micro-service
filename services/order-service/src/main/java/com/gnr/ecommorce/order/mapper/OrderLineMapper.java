package com.gnr.ecommorce.order.mapper;

import com.gnr.ecommorce.order.entity.Order;
import com.gnr.ecommorce.order.entity.OrderLine;
import com.gnr.ecommorce.order.record.OrderLineRequest;
import com.gnr.ecommorce.order.record.OrderLineResponse;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {


    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .quantity(orderLineRequest.quantity())
                .order(Order.builder()
                        .id(orderLineRequest.orderId())
                        .build())
                .productId(orderLineRequest.productId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLineResponse orderLineResponse) {
        return new OrderLineResponse(orderLineResponse.id(), orderLineResponse.quantity());
    }
}
