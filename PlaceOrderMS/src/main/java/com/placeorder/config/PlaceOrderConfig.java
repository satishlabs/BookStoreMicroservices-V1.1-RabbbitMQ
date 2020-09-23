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

@SpringBootApplication
public class PlaceOrderConfig implements WebMvcConfigurer{
	
	public static final String INVENTORY_QUEUE= "MyInventory-Queue"; 
	public static final String INVENTORY_EXCHANGE = "MyInventory-Exchange";
	
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
}
