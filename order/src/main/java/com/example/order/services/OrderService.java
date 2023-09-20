package com.example.order.services;

import com.example.order.models.Order;
import com.example.order.models.OrderPlaceEvent;
import com.example.order.models.PlaceOrderRequest;
import com.example.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final KafkaTemplate kafkaTemplate;
    private final OrderRepository orderRepository;
    public void placeOrder(PlaceOrderRequest request) {
        //save db
        Order order = new Order();
        order.setPrice(request.getPrice());
        order.setProduct(request.getProduct());
        order.setStatus("PLACED");
        order.setCreatedAt(LocalDate.now());
        order.setProductCount(3);
        Order o = this.orderRepository.save(order);
        //publish event
        this.kafkaTemplate.send("prod.orders.placed", String.valueOf(o.getId()), OrderPlaceEvent.builder()
                                                                                        .product(request.getProduct())
                                                                                        .price(request.getPrice())
                                                                                        .orderId(order.getId().intValue())
                                                                                        .createdAt(order.getCreatedAt())
                                                                                        .productCount(order.getProductCount())
                                                                                        .build());
    }

    @KafkaListener(topics = "prod.orders.shipped", groupId = "order-group")
    public void handleOrderShippedEvent(String orderId) {
        this.orderRepository.findById(Long.valueOf(orderId)).ifPresent(order -> {
            order.setStatus("SHIPPED");
            this.orderRepository.save(order);
        });
    }
}
