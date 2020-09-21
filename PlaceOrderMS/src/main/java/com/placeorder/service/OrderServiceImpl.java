package com.placeorder.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.placeorder.dao.BookInventoryDAO;
import com.placeorder.dao.OrderDAO;
import com.placeorder.dao.OrderItemDAO;
import com.placeorder.dto.OrderInfo;
import com.placeorder.entity.BookInventory;
import com.placeorder.entity.Order;
import com.placeorder.entity.OrderItem;

@Service
public class OrderServiceImpl implements OrderService{
	static Logger log=LoggerFactory.getLogger(OrderServiceImpl.class); 

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private OrderItemDAO orderItemDAO;

	@Autowired
	private BookInventoryDAO bookInventoryDAO;

	@Override
	public void placeOrder(OrderInfo orderInfo) {
		log.info("---OrderServiceImpl---placeOrder()-----"); 
		//Place Order
		// Task1: Insert Order  - 1 
		Order myorder = orderInfo.getOrder();
		myorder = orderDAO.save(myorder);
		int orderId = myorder.getOrderId();

		// Task2: Insert OrderItems  - N** need to improve this
		List<OrderItem> itemList = orderInfo.getItemList();
		System.out.println("%%%%%%%%%%%%%%%%%%%%% + *********** "+itemList);
		for(OrderItem myOrderItem: itemList) {
			myOrderItem.setOrderId(orderId);
			orderItemDAO.save(myOrderItem);
		}

		//(Task4)
		//Update Local Inventory
		//Update Inventory of BookSearchMS
		RestTemplate bookSearchRest=new RestTemplate(); 
		String endpoint="http://localhost:8000/updateBookInventory"; 
		
		
		// Task3: Update Local Book Inventory - N
		// Task4: Update BookSearchMS BookInventory - N
		for(OrderItem myorderItem: itemList) {
			Integer bookId = myorderItem.getBookId();
			BookInventory mybookInventory =  bookInventoryDAO.findById(bookId).get();
			Integer currentStock = mybookInventory.getBooksAvailable();
			currentStock = currentStock-myorderItem.getQty();
			mybookInventory.setBooksAvailable(currentStock);
			
			//Local Inventory
			bookInventoryDAO.save(mybookInventory);
			
			//Inventory of BookSearchMS
			bookSearchRest.put(endpoint, mybookInventory);
			
			//bookInventoryDAO.save(mybookInventory); //Update 
		}
	}

	@Override
	public List<Order> getOrdersByUserId(String userId) {
		log.info("---OrderServiceImpl---getOrderByUserId()-----"); 
		List<Order> orderList =  orderDAO.getOrdersByUserId(userId);
		return orderList;
	}

	@Override
	public Order getOrderByOrderId(Integer orderId) {
		log.info("---OrderServiceImpl---getOrderByOrderId()-----"); 
		Order myorder = orderDAO.findById(orderId).get();
		return myorder;
	}

}
