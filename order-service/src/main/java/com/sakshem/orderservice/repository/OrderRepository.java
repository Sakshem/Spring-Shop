package com.sakshem.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sakshem.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    
}
