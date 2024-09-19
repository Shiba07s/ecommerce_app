package com.cartService.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartService.payload.CartItemDto;
import com.cartService.payload.CartItemUpdateRequest;
import com.cartService.services.CartItemService;

@RestController
@RequestMapping("/api/v1/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/create")
    public ResponseEntity<CartItemDto> createCartItem(@RequestBody CartItemDto cartItemDTO) {
        CartItemDto createdCartItem = cartItemService.createNewCart(cartItemDTO);
        return ResponseEntity.ok(createdCartItem);
    }

    @GetMapping
    public ResponseEntity<List<CartItemDto>> getAllCartItems() {
        List<CartItemDto> cartItems = cartItemService.getAllCartDetails();
        return new ResponseEntity<List<CartItemDto>>(cartItems,HttpStatus.OK);
    }
    
    @GetMapping("/{itemId}")
    public ResponseEntity<CartItemDto> getcartDetailsById(@PathVariable Long itemId){
    	CartItemDto cartItemById = cartItemService.getCartItemById(itemId);
    	return new ResponseEntity<CartItemDto>(cartItemById,HttpStatus.OK) ;
    }
    
    @PutMapping("/update/{itemId}")
    public ResponseEntity<CartItemDto> updateCartDetails(
            @PathVariable Long itemId,
            @RequestBody CartItemUpdateRequest request) {
         CartItemDto updateCartDetails = cartItemService.updateCartDetails(itemId, request);
    	return new ResponseEntity<CartItemDto>(updateCartDetails,HttpStatus.OK) ;
    }
    
//    @PutMapping("/update/{itemId}")
//    public ResponseEntity<CartItemDto> updateCartDetails(@PathVariable Long itemId, 
//                                                         @RequestBody CartItemUpdateRequest request) {
//        try {
//            CartItemDto updatedCartDto = cartItemService.updateCartDetails(itemId, request);
//            return ResponseEntity.ok(updatedCartDto);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
    
    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<CartItemDto>deleteCartDetails(@PathVariable Long itemId){
    	CartItemDto deleteCart = cartItemService.deleteCart(itemId);
		return new ResponseEntity<CartItemDto>(deleteCart,HttpStatus.NO_CONTENT);
    	
    }
    
    @PutMapping("/{orderId}/update-is-ordered")
    public ResponseEntity<CartItemDto> updateIsOrdered(
            @PathVariable Long orderId) {
         CartItemDto updateIsOrderedStatus = cartItemService.updateIsOrderedStatus(orderId);
    	return new ResponseEntity<CartItemDto>(updateIsOrderedStatus,HttpStatus.OK) ;
    }
    
 
}
