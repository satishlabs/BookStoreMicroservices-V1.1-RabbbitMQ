package com.booksearch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
 
public class MyBookSearchApplication implements CommandLineRunner{

	
	public static void main(String[] args) {
		System.out.println(" BookSearch - Begin ");
		SpringApplication.run(MyBookSearchApplication.class, args);
		System.out.println(" BookSearch - End "); 
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(" BookSearch - Launched.... "); 
	}

}
