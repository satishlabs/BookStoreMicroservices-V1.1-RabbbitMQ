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

/*
 UserRatingMS sends the message to BookSearchMS via Rabbit MQ Asynchronously.
So we need to use Rabbit MQ in UserRatingMS. 
 * */

@SpringBootApplication
public class UserRatingsConfig {
	//UserRatingMS sends the message to BookSearchMS via Rabbit MQ Asynchronously
	public static final String RATINGS_QUEUE= "MyRatings-Queue"; 
	public static final String RATINGS_EXCHANGE = "MyRatings-Exchange";


	public static final String USER_RATING_QUEUE= "MyUserRating-Queue";
	public static final String USER_RATING_EXCHANGE= "MyUserRating-Exchange"; 


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


	@Bean(name="myUserRatingQueue")
	Queue createUserRatingQueue() {
		return QueueBuilder.durable(USER_RATING_QUEUE).build();
	}
	@Bean(name="myUserRatingExchange")
	Exchange createUserRatingExchange() {
		return ExchangeBuilder.topicExchange(USER_RATING_EXCHANGE).build();
	}
	@Bean
	Binding userRatingBinding(Queue myUserRatingQueue, TopicExchange myUserRatingExchange) {
		return BindingBuilder.bind(myUserRatingQueue).to(myUserRatingExchange)
				.with(USER_RATING_QUEUE);
	} 

}
