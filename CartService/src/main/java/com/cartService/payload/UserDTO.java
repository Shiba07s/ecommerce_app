package com.cartService.payload;

 
import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String name;
    private String email;
    private String address;
    private long phoneNumber;
}
