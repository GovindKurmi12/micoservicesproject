package com.gk.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gk.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
