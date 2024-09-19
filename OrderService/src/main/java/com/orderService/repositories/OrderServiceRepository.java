package com.orderService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orderService.entities.Order;

public interface OrderServiceRepository extends JpaRepository<Order, Long> {

}
