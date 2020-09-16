package com.placeorder.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placeorder.entity.Order;

public interface OrderDAO extends JpaRepository<Order, Integer>{

	List<Order> getOrdersByUserId(String userId);

}
