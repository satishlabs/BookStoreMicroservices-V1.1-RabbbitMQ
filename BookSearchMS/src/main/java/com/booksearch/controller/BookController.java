package com.booksearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.booksearch.dto.BookInfo;
import com.booksearch.entity.Book;
import com.booksearch.entity.BookInventory;
import com.booksearch.entity.BookRating;
import com.booksearch.service.BookService;

@CrossOrigin
@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/mybooks/{author}/{category}")
	public List<Book> getBooks(@PathVariable String author, @PathVariable String category) {
		System.out.println(author + "\t" + category);
		return bookService.getBooks(author, category);
	}

	@GetMapping("/mybook/{bookId}")
	public BookInfo getBookById(@PathVariable Integer bookId) {
		System.out.println("--BookController--- getBookById()---");
		return bookService.getBookInfo(bookId);
	}

}
