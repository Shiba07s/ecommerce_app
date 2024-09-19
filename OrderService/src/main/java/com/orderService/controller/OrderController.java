package com.orderService.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.orderService.payload.OrderDto;
import com.orderService.services.OrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

	private final OrderService orderService;

	@PostMapping("/create")
	public ResponseEntity<OrderDto> createNewOrder(@RequestBody OrderDto OrderDto) {
		OrderDto orderItem = orderService.createNewOrder(OrderDto);
		return new ResponseEntity<OrderDto>(orderItem, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<OrderDto>> getAllOrderItemDetails() {
		List<OrderDto> allOrderDetails = orderService.getAllOrderDetails();
		return new ResponseEntity<List<OrderDto>>(allOrderDetails, HttpStatus.OK);
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderDto>getByOrderItemId(@PathVariable Long orderId){
		OrderDto dataFromOrderId = orderService.getOrderById(orderId);
		return new ResponseEntity<OrderDto>(dataFromOrderId,HttpStatus.OK);
	}

}
