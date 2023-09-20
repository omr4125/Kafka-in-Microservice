package com.example.order.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class OrderPlaceEvent {
    private int orderId;
    private String product;
    private double price;
    private LocalDate createdAt;
    private int productCount;
}
