package com.productService.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.productService.Payload.ProductDto;
import com.productService.entities.Product;
import com.productService.exception.ProductNotFoundException;
import com.productService.repositories.ProductRepository;
import com.productService.services.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
	private final ModelMapper modelMapper;

	@Override
	public ProductDto createProduct(ProductDto productDto) {
		Product map = modelMapper.map(productDto, Product.class);
		Product save = productRepository.save(map);
 		return modelMapper.map(save, ProductDto.class);
	}

	@Override
	public ProductDto getProductById(Long productId) {
		Product orElseThrow = productRepository.findById(productId).orElseThrow( ()-> new ProductNotFoundException("product not found with this id : "+productId));
		return modelMapper.map(orElseThrow, ProductDto.class);
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> all = productRepository.findAll();
		return all.stream().map(products -> modelMapper.map(products, ProductDto.class)).collect(Collectors.toList());
	}

	@Override
	public ProductDto updateProduct(Long productId, ProductDto productDto) {
		Product product = productRepository.findById(productId).orElseThrow( ()-> new ProductNotFoundException("product not found with this id : "+productId));

		product.setName(productDto.getName());
		product.setCategory(productDto.getCategory());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		
		Product save = productRepository.save(product);
 		return modelMapper.map(save, ProductDto.class);
	}

	@Override
	public void deleteProduct(Long productId) {
		Product product = productRepository.findById(productId).orElseThrow( ()-> new ProductNotFoundException("product not found with this id : "+productId));
		productRepository.delete(product);
		
	}

}
