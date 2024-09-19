package com.orderService.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.orderService.clients.CartClient;
import com.orderService.clients.ProductClient;
import com.orderService.entities.OrderItem;
import com.orderService.exception.CartNotFoundException;
import com.orderService.exception.OrderItemNotFoundException;
import com.orderService.exception.ProductNotFoundException;
import com.orderService.payload.CartItemDto;
import com.orderService.payload.OrderItemDto;
import com.orderService.payload.ProductDto;
import com.orderService.repositories.OrderItemRepository;
import com.orderService.services.OrderItemService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderItemServiceImpl implements OrderItemService {

	private final OrderItemRepository orderItemRepository;
	private final ModelMapper modelMapper;
	private final CartClient cartClient;
	private final ProductClient productClient;

	@Override
	public OrderItemDto createOrderItem(OrderItemDto orderItemDto) {
		OrderItem order = modelMapper.map(orderItemDto, OrderItem.class);
		
		//find the client id
		CartItemDto cartItemDto = cartClient.getCartItemById(orderItemDto.getCartItemId());
		ProductDto productDto = productClient.getProductbyId(orderItemDto.getProductId());

		if (cartItemDto == null) {
			throw new CartNotFoundException("Cart is not found ");
		}
		if (productDto == null) {
			throw new ProductNotFoundException("Product not found with this id");
		}
		
		OrderItem save = orderItemRepository.save(order);
		
		return modelMapper.map(save, OrderItemDto.class);
	}

	@Override
	public List<OrderItemDto> getAllOrderItemDetails() {
		List<OrderItem> all = orderItemRepository.findAll();
		return all.stream().map(orders->{
			OrderItemDto map = modelMapper.map(orders, OrderItemDto.class);
			CartItemDto cartItemDto = cartClient.getCartItemById(orders.getCartItemId());
			ProductDto productDto = productClient.getProductbyId(orders.getProductId());
			
			map.setCartDertails(cartItemDto);
			map.setProductDetails(productDto);
			return map;
			
		})
				.collect(Collectors.toList());
	}

	@Override
	public OrderItemDto getDataFromOrderItemId(Long orderItemId) {
		
		OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow( ()-> new OrderItemNotFoundException("order item not found with this id: "+orderItemId));
		OrderItemDto map = modelMapper.map(orderItem, OrderItemDto.class);
		CartItemDto cartItemDto = cartClient.getCartItemById(orderItem.getCartItemId());
		ProductDto productDto = productClient.getProductbyId(orderItem.getProductId());
		map.setCartDertails(cartItemDto);
		map.setProductDetails(productDto);

		return map;
	}

}
