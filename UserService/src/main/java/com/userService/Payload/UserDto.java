package com.userService.Payload;

 import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
    private Long userId;
    private String name;
    private String email;
    private String address;
    private long phoneNumber;

}
