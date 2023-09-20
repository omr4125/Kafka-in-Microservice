package com.example.shipping.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Shipping {

    @Id
    @GeneratedValue
    private Long id;
    private int orderId;
}
