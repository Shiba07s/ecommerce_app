package com.productService.services;

import java.util.List;

import com.productService.Payload.ProductDto;

public interface ProductService {

	ProductDto createProduct(ProductDto productDto);

    // Get a product by its ID
    ProductDto getProductById(Long productId);

    // Get all products
    List<ProductDto> getAllProducts();

    // Update an existing product
    ProductDto updateProduct(Long productId, ProductDto productDto);

    // Delete a product by its ID
    void deleteProduct(Long productId);
}
