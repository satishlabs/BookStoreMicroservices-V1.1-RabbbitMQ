package com.userratings.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserRatingsConfig {
	public static final String RATINGS_QUEUE= "MyRatings-Queue"; 
	public static final String RATINGS_EXCHANGE = "MyRatings-Exchange";
	
	
	@Bean(name = "myRatingQueue")
	Queue creatingRatings(){
		return QueueBuilder.durable(RATINGS_QUEUE).build();
	}
	
	@Bean(name = "myRatingExchange")
	Exchange createRatingExchange() {
		return ExchangeBuilder.topicExchange(RATINGS_EXCHANGE).build();
	}
	
	@Bean
	Binding ratingBinding(Queue myRatingQueue, TopicExchange myRatingExchange) {
		return BindingBuilder.bind(myRatingQueue).to(myRatingExchange).with(RATINGS_QUEUE);
	}
	
}
