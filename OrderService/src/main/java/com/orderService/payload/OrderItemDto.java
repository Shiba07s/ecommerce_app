package com.orderService.payload;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDto {
	
    private Long id;
    private Long cartItemId;   
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
    private CartItemDto cartDertails;
    private ProductDto productDetails;

}
