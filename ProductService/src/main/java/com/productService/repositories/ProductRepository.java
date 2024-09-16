package com.productService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productService.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
