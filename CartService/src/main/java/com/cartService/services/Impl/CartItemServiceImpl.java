package com.cartService.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.cartService.clients.UserClient;
import com.cartService.entities.CartItem;
import com.cartService.payload.CartItemDTO;
import com.cartService.payload.UserDTO;
import com.cartService.repositories.CartItemRepository;
import com.cartService.services.CartItemService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartItemServiceImpl implements CartItemService {
	
	private final CartItemRepository cartItemRepository;
	private final ModelMapper modelMapper;
	private final UserClient userClient;

	@Override
	public CartItemDTO createNewCart(CartItemDTO cartItemDTO) {
		
		CartItem map = modelMapper.map(cartItemDTO, CartItem.class);
		CartItem save = cartItemRepository.save(map);
		return modelMapper.map(save, CartItemDTO.class);
	}

	@Override
    public List<CartItemDTO> getAllDetails() {
        List<CartItem> cartItems = cartItemRepository.findAll();
        return cartItems.stream()
                        .map(cartItem -> {
                            CartItemDTO dto = modelMapper.map(cartItem, CartItemDTO.class);
                            // Fetch user details if needed
                            if (cartItem.getUserId() != null) {
                                UserDTO user = userClient.getUserById(cartItem.getUserId());
                                dto.setUser(user);
                            }
                            return dto;
                        })
                        .collect(Collectors.toList());
    }

}
