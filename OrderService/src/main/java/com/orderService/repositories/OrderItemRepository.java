package com.orderService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orderService.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
