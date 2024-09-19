package com.cartService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cartService.payload.UserDto;

@FeignClient(name="user-service",url = "http://localhost:7081")
public interface UserClient {
	
	@GetMapping("/api/v1/users/{userId}")
	UserDto getUserById(@PathVariable Long userId);
	
	@DeleteMapping("/api/v1/users/delete/{userId}")
	UserDto deleteByUserId(@PathVariable Long userId);
	
    @PutMapping("/api/v1/users/update/{userId}")
    UserDto updateByUserId(@PathVariable Long userId, @RequestBody UserDto userDto);
}
