package com.cartService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cartService.payload.UserDTO;

@FeignClient(name="user-service",url = "http://localhost:8081")
public interface UserClient {
	
	@GetMapping("/api/v1/users/{userId}")
	UserDTO getUserById(@PathVariable Long userId);

}
