package com.cartService.controller;


import com.cartService.payload.CartItemDTO;
import com.cartService.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<CartItemDTO> createCartItem(@RequestBody CartItemDTO cartItemDTO) {
        CartItemDTO createdCartItem = cartItemService.createNewCart(cartItemDTO);
        return ResponseEntity.ok(createdCartItem);
    }

    @GetMapping
    public ResponseEntity<List<CartItemDTO>> getAllCartItems() {
        List<CartItemDTO> cartItems = cartItemService.getAllDetails();
        return ResponseEntity.ok(cartItems);
    }
    
//    @GetMapping("/{itemId}")
//    public ResponseEntity<CartItemDTO> getCartItemById(@PathVariable Long itemId) {
//        // Assuming you have a method to get CartItem by id, similar to createNewCart()
//        // This method should be added in the service layer and implemented
//        CartItemDTO cartItemDTO = cartItemService.getCartItemById(itemId); 
//        return cartItemDTO != null ? ResponseEntity.ok(cartItemDTO) : ResponseEntity.notFound().build();
//    }
}
