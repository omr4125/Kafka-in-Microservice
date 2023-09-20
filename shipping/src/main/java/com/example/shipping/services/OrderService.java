package com.example.shipping.services;

import com.example.shipping.models.OrderPlacedEvent;
import com.example.shipping.models.Shipping;
import com.example.shipping.repositories.ShippingRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final KafkaTemplate kafkaTemplate;
    private final ShippingRepository shippingRepository;

    @KafkaListener(topics = "prod.orders.placed", groupId = "shipping-group")
    public  void handleOrderPlacedEvent(OrderPlacedEvent event){
        Shipping shipping = new Shipping();
        shipping.setOrderId(event.getOrderId());
        //save to db
        this.shippingRepository.save(shipping);
        //publish
        this.kafkaTemplate.send("prod.orders.shipped", String.valueOf(shipping.getOrderId()), String.valueOf(shipping.getOrderId()));
    }
}
