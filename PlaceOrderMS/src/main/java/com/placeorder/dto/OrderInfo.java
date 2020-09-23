package com.placeorder.dto;

import java.util.List;

import com.placeorder.entity.Order;
import com.placeorder.entity.OrderItem;

public class OrderInfo {
	private Order order;
	private List<OrderItem> itemList;
	
	public OrderInfo() {}
	public OrderInfo(Order order, List<OrderItem> itemList) {
		super();
		this.order = order;
		this.itemList = itemList;
	}
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public List<OrderItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<OrderItem> itemList) {
		this.itemList = itemList;
	}
	@Override
	public String toString() {
		return "OrderInfo [order=" + order + ", itemList=" + itemList + "]";
	}
	
	
	
}
