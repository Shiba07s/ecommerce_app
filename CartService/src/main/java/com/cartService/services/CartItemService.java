package com.cartService.services;

import java.util.List;

import com.cartService.payload.CartItemDto;
import com.cartService.payload.CartItemUpdateRequest;

public interface CartItemService {
	
	CartItemDto createNewCart(CartItemDto cartItemDto);
	CartItemDto getCartItemById(Long itemId);
	List<CartItemDto> getAllCartDetails();
	CartItemDto updateCartDetails(Long itemId, CartItemUpdateRequest request);
	CartItemDto deleteCart(Long itemId);
	CartItemDto updateIsOrderedStatus(Long orderId);
	
 	
//	List<CartItemDTO> getAllDetails();
	

}
