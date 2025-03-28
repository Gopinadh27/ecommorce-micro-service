package com.gnr.ecommorce.order.service;

import com.gnr.ecommorce.order.customer.feign.CustomerClient;
import com.gnr.ecommorce.order.event.record.OrderConfirmation;
import com.gnr.ecommorce.order.exception.BusinessException;
import com.gnr.ecommorce.order.kafka.OrderEventsProducer;
import com.gnr.ecommorce.order.mapper.OrderMapper;
import com.gnr.ecommorce.order.payment.client.PaymentClient;
import com.gnr.ecommorce.order.record.OrderLineRequest;
import com.gnr.ecommorce.order.record.OrderRequest;
import com.gnr.ecommorce.order.product.client.ProductClient;
import com.gnr.ecommorce.order.record.OrderResponse;
import com.gnr.ecommorce.order.record.PaymentRequest;
import com.gnr.ecommorce.order.record.PurchaseRequest;
import com.gnr.ecommorce.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final CustomerClient customerClient;

    private final ProductClient productClient;

    private final PaymentClient paymentClient;

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final OrderLineService orderLineService;

    private final OrderEventsProducer orderEventsProducer;

    @Override
    public Integer createOrder(OrderRequest orderRequest) {

        //check customer validation  --> customer service

        var customer = customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Can't create order :: No customer exists with" +
                        " the provided id::"+orderRequest.customerId()));

        //purchase the product   ---> product service

        var purchasedProducts = productClient.purchaseProducts(orderRequest.products());

        //persist order

        var order = orderRepository.save(orderMapper.toOrder(orderRequest));

        //persist order lines

        for(PurchaseRequest orderLine: orderRequest.products()) {
            orderLineService.saveOrderLine(
                new OrderLineRequest(
                        null,
                        order.getId(),
                        orderLine.productId(),
                        orderLine.quantity()
                )
            );
        }

        //todo initiate payment

        var paymentId = paymentClient.requestOrderPayment(
                new PaymentRequest(
                    orderRequest.amount(),
                    orderRequest.paymentMethod(),
                    order.getId(),
                    order.getReference(),
                    customer
                )
        );

        //send order confirmation --> notification service

        orderEventsProducer.sendOrderConfirmation(
            new OrderConfirmation(
                    orderRequest.reference(),
                    orderRequest.amount(),
                    orderRequest.paymentMethod(),
                    customer,
                    purchasedProducts
            )
        );

        return order.getId();
    }

    @Override
    public List<OrderResponse> findAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::fromOrder)
                .toList();
    }

    @Override
    public OrderResponse findOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException("No order found with the provided id:: "
                        +orderId));
    }


}
