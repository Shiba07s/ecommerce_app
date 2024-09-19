package com.orderService.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.orderService.clients.CartClient;
import com.orderService.entities.Order;
import com.orderService.exception.OrderNotFoundException;
import com.orderService.payload.OrderDto;
import com.orderService.repositories.OrderServiceRepository;
import com.orderService.services.OrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
	
	private final OrderServiceRepository orderServiceRepository;
	private final ModelMapper modelMapper;
	private CartClient cartClient;

	@Override
	public OrderDto createNewOrder(OrderDto orderDto) {
		Order order = modelMapper.map(orderDto, Order.class);
 		
		Order save = orderServiceRepository.save(order);
		
//		cartClient.updateIsOrderedStatus(save.getId());
		
		return modelMapper.map(save, OrderDto.class);
	}

	@Override
	public List<OrderDto> getAllOrderDetails() {
		List<Order> all = orderServiceRepository.findAll();
		return all.stream().map(orders-> modelMapper.map(orders,OrderDto.class)).collect(Collectors.toList());
	}

	@Override
	public OrderDto getOrderById(Long orderId) {
		Order order = orderServiceRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException("order not found with this id : "+orderId));
		return modelMapper.map(order, OrderDto.class);
	}

}
