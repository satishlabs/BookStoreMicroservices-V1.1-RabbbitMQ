package com.booksearch.service;

import java.util.List;

import com.booksearch.dto.BookInfo;
import com.booksearch.entity.Book;
import com.booksearch.entity.BookInventory;
import com.booksearch.entity.BookRating;

public interface BookService {
	public List<Book> getBooks(String author, String category);

	public BookInfo getBookInfo(Integer bookId);

}
