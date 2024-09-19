package com.cartService.payload;


import lombok.Data;

@Data
public class CartItemDto {
    private Long itemId;
    private Long productId;
    private int quantity;
    private Long userId;
    private UserDto userDto;
    private ProductDto productDto;
    private Long orderId;  // Reference to Order, null if not yet ordered
    private boolean isOrdered=false;
 

}
