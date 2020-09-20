package com.bookstoreweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.bookstoreweb.*"})
public class BookStoreWebApplication implements CommandLineRunner{
	
	static Logger log = LoggerFactory.getLogger(BookStoreWebApplication.class);
	
	public static void main(String[] args) {
		log.info("BookStoreWeb -- Being");
		SpringApplication.run(BookStoreWebApplication.class, args);
		log.info("BookStoreWeb -- End");
	}

	public void run(String... args) throws Exception {
		log.info("BookStoreWeb -- Launched....");
	}

}