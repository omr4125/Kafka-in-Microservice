package com.example.order.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderRequest {
    private String product;
    private double price;
    private LocalDate createdAt;
    private int productCount;
}
