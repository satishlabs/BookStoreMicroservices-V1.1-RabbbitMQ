package com.placeorder.service;

import java.util.List;

import com.placeorder.dto.OrderInfo;
import com.placeorder.entity.Order;

public interface OrderService {
	public void placeOrder(OrderInfo orderInfo);
	public List<Order> getOrdersByUserId(String userId);
	public Order getOrderByOrderId(Integer orderId);
	
}
