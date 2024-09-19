package com.cartService.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemUpdateRequest {
    
//    @JsonProperty("cart_item")
    private CartItemDto cartItemDto;
    
//    @JsonProperty("user_details")
    private UserDto userDto;
    
//    @JsonProperty("product_info")
    private ProductDto productDto;
}