package com.gnr.ecommorce.order.service;

import com.gnr.ecommorce.order.mapper.OrderLineMapper;
import com.gnr.ecommorce.order.record.OrderLineRequest;
import com.gnr.ecommorce.order.record.OrderLineResponse;
import com.gnr.ecommorce.order.repository.OrderLineRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineServiceImpl implements OrderLineService{

    private final OrderLineRepository orderLineRepository;

    private final OrderLineMapper orderLineMapper;

    @Override
    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var orderLine = orderLineMapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(orderLine).getId();
    }

    @Override
    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderLineMapper::toOrderLineResponse)
                .toList();
    }

}
