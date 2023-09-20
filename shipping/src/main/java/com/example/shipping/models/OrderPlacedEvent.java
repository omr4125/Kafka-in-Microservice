package com.example.shipping.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPlacedEvent {

    private int orderId;
    private String product;
    private double price;
    private LocalDate createdAt;
    private int productCount;
}
