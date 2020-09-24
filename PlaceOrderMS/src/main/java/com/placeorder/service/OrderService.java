package com.placeorder.service;

import java.util.List;

import com.placeorder.entity.MyOrder;

public interface OrderService {
	//public void placeOrder(OrderInfo orderInfo);
	public List<MyOrder> getOrdersByUserId(String userId);
	public MyOrder getOrderByOrderId(Integer orderId);
	
}
