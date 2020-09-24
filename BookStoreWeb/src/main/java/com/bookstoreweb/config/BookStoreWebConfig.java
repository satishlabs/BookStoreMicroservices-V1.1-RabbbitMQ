package com.bookstoreweb.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/*
BookStoreWeb sends the message to PlaceOrderMS via Rabbit MQ Asynchronously
BookStoreWeb sends the message to UserRatingMS via Rabbit MQ Asynchronously
So we need to use Rabbit MQ in BookStoreWeb. 
 * */

@SpringBootApplication 
public class BookStoreWebConfig implements WebMvcConfigurer{
	//BookStoreWeb sends the message to PlaceOrderMS via Rabbit MQ Asynchronously
	public static final String ORDER_QUEUE= "MyOrder-Queue"; 
	public static final String ORDER_EXCHANGE = "MyOrder-Exchange";

	//BookStoreWeb sends the message to UserRatingMS via Rabbit MQ Asynchronously
	public static final String USER_RATING_QUEUE= "MyUserRating-Queue";
	public static final String USER_RATING_EXCHANGE= "MyUserRating-Exchange"; 

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/myjsps/"); 
		resolver.setSuffix(".jsp"); 
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) { 
		registry.addResourceHandler("/webjars/**") 
		.addResourceLocations("classpath:/META-INF/resources/webjars/"); 
		registry.addResourceHandler("/mycss/**")
		.addResourceLocations("classpath:/META-INF/resources/mycss/"); 
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

	//------------------------------------------
	
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
		return BindingBuilder.bind(myUserRatingQueue)
				.to(myUserRatingExchange).with(USER_RATING_QUEUE);
	} 


}
