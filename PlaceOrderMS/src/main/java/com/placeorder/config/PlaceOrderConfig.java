package com.placeorder.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 PlaceOrderMS sends the message to BookSearchMS via Rabbit MQ Asynchronously
PlaceOrderMS Receives the message to BookStoreWeb via Rabbit MQ Asynchronously
 So we need to use Rabbit MQ in PlaceOrderMS. 

 * */

@SpringBootApplication
public class PlaceOrderConfig implements WebMvcConfigurer{
	//PlaceOrderMS sends the message to BookSearchMS via Rabbit MQ Asynchronously 
	public static final String INVENTORY_QUEUE= "MyInventory-Queue"; 
	public static final String INVENTORY_EXCHANGE = "MyInventory-Exchange";
	
	
	//PlaceOrderMS Receives the message to BookStoreWeb via Rabbit MQ Asynchronously 
	public static final String ORDER_QUEUE= "MyOrder-Queue"; 
	public static final String ORDER_EXCHANGE = "MyOrder-Exchange";
	
	//Inventory
	@Bean(name = "myInventorygQueue")
	Queue creatingInventory(){
		return QueueBuilder.durable(INVENTORY_QUEUE).build();
	}
	
	@Bean(name = "myInventoryExchange")
	Exchange createInventoryExchange() {
		return ExchangeBuilder.topicExchange(INVENTORY_EXCHANGE).build();
	}
	@Bean
	Binding inventoryBinding(Queue myInventorygQueue, TopicExchange myInventoryExchange) {
		return BindingBuilder.bind(myInventorygQueue).to(myInventoryExchange).with(INVENTORY_QUEUE);
	}
	
	
	@Bean(name = "myOrderQueue")
	Queue creatingOrderQueue(){
		return QueueBuilder.durable(ORDER_QUEUE).build();
	}
	
	@Bean(name = "myOrderExchange")
	Exchange createOrderExchange() {
		return ExchangeBuilder.topicExchange(ORDER_EXCHANGE).build();
	}
	
	@Bean
	Binding orderBinding(Queue myOrderQueue, TopicExchange myOrderExchange) {
		return BindingBuilder.bind(myOrderQueue).to(myOrderExchange).with(ORDER_QUEUE);
	}
}
