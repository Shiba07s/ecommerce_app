package com.cartService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cartService.entities.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	CartItem findByOrderId(Long orderId);

}
