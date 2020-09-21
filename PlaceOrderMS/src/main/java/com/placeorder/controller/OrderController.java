package com.placeorder.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.placeorder.dto.OrderInfo;
import com.placeorder.entity.Order;
import com.placeorder.service.OrderService;

@CrossOrigin
@RestController
public class OrderController {
	static Logger log=LoggerFactory.getLogger(OrderController.class); 
	
	@Autowired
	private OrderService orderService;
	
	@PutMapping("/placeOrder")
	public void placeOrder(@RequestBody OrderInfo orderInfo) {
		log.info("---OrderController---placeOrder()-----");
		orderService.placeOrder(orderInfo);
	}
	
	@GetMapping("/myorders/{userId}")
	public List<Order> getOrdersByUserId(@PathVariable String userId){
		List<Order> orderList = orderService.getOrdersByUserId(userId);
		return orderList;
	}
	
	@GetMapping("/myorder/{orderId}")
	public Order getOrderByOrderId(@PathVariable Integer orderId) {
		Order myorder=orderService.getOrderByOrderId(orderId);   
		return myorder; 
	}
}
