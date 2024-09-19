package com.orderService.services;

import java.util.List;

import com.orderService.payload.OrderItemDto;

public interface OrderItemService {
	
	OrderItemDto createOrderItem(OrderItemDto orderItemDto);
	List<OrderItemDto> getAllOrderItemDetails();
	OrderItemDto getDataFromOrderItemId(Long orderItemId);

}
