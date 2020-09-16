package com.placeorder.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placeorder.entity.OrderItem;

public interface OrderItemDAO extends JpaRepository<OrderItem, Integer>{

}
