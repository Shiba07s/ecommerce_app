package com.cartService.payload;


import lombok.Data;

@Data
public class CartItemDTO {
    private Long itemId;
    private Long productId;
    private int quantity;
    private Long userId;
    private UserDTO user; // Optional: to include user details directly in CartItemDTO
}
