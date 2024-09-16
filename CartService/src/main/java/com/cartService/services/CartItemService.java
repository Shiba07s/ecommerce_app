package com.cartService.services;

import java.util.List;

import com.cartService.payload.CartItemDTO;

public interface CartItemService {
	
	CartItemDTO createNewCart(CartItemDTO cartItemDTO);
	
	List<CartItemDTO> getAllDetails();
	

}
