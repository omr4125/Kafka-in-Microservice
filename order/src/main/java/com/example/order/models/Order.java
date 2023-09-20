package com.example.order.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue
    private Long Id;

    private String product;
    private double price;
    private String status;
    private LocalDate createdAt;
    private int productCount;

}
