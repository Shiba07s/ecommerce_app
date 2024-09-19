package com.orderService.services;

import java.util.List;

import com.orderService.payload.OrderDto;


public interface OrderService {
	
	OrderDto createNewOrder(OrderDto orderDto);
	List<OrderDto>getAllOrderDetails();
	OrderDto getOrderById(Long orderId);

}
