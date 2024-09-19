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

import com.orderService.payload.OrderItemDto;
import com.orderService.services.OrderItemService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order-items")
public class OrderItemController {

	private final OrderItemService orderItemService;

	@PostMapping("/create")
	public ResponseEntity<OrderItemDto> createNewOrderItem(@RequestBody OrderItemDto orderItemDto) {
		OrderItemDto orderItem = orderItemService.createOrderItem(orderItemDto);
		return new ResponseEntity<OrderItemDto>(orderItem, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<OrderItemDto>> getAllOrderItemDetails() {
		List<OrderItemDto> allOrderItemDetails = orderItemService.getAllOrderItemDetails();
		return new ResponseEntity<List<OrderItemDto>>(allOrderItemDetails, HttpStatus.OK);
	}
	
	@GetMapping("/{orderItemId}")
	public ResponseEntity<OrderItemDto>getByOrderItemId(@PathVariable Long orderItemId){
		OrderItemDto dataFromOrderItemId = orderItemService.getDataFromOrderItemId(orderItemId);
		return new ResponseEntity<OrderItemDto>(dataFromOrderItemId,HttpStatus.OK);
	}

}
