package com.cartService.entities;


import com.cartService.payload.UserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carts_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    private Long productId;
    private int quantity;
    private Long userId;

    // You might want to add a method to set user details from UserDTO
    // if you are including it directly in the CartItem response
    private UserDTO user; // Optional: only if you plan to include user details directly in CartItem
}

