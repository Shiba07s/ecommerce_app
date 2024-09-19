package com.orderService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.orderService.payload.CartItemDto;

@FeignClient(name="cart-service",url = "${cart.service.url}")
public interface CartClient {
	
	@GetMapping("/api/v1/cart-items/{itemId}")
	CartItemDto getCartItemById(@PathVariable Long itemId);
	
    @PutMapping("/api/cart/items/{orderId}/update-is-ordered")
    CartItemDto updateIsOrderedStatus(@PathVariable Long orderId);

}
