package com.example.shipping.repositories;

import com.example.shipping.models.Shipping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends CrudRepository<Shipping, Long> {
}
