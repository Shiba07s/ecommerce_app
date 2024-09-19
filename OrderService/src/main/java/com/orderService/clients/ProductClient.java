package com.orderService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.orderService.payload.ProductDto;
@FeignClient(name = "product-service", url = "http://localhost:7082")
public interface ProductClient {

	@GetMapping("/api/v1/products/{productId}")
	ProductDto getProductbyId(@PathVariable Long productId);

	@DeleteMapping("/api/v1/products/delete/{productId}")
	ProductDto deleteProductbyId(@PathVariable Long productId);

	@PutMapping("/api/v1/products/update/{productId}")
	ProductDto updateByProductId(@PathVariable Long productId, @RequestBody ProductDto productDto);

}
