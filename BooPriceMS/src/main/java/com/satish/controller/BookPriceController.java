package com.satish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.satish.entity.BookPrice;
import com.satish.service.BookPriceService;

@CrossOrigin
@RestController
public class BookPriceController {

	@Autowired
	private BookPriceService bookPriceService;

	@GetMapping("/bookPrice/{bookId}")
	public BookPrice getBookPrice(@PathVariable Integer bookId) {
		System.out.println("---BookPriceController---getBookPrice()-----");
		return bookPriceService.getBookPriceById(bookId);
	}

	@GetMapping("/offeredPrice/{bookId}")
	public double getOfferedPrice(@PathVariable Integer bookId) {
		System.out.println("---BookPriceController---getOfferedPrice()-----");
		return bookPriceService.getOfferedPriceById(bookId);
	}
}
