package com.satish.service;

import java.util.List;

import com.satish.dto.BookInfo;
import com.satish.entity.Book;
import com.satish.entity.BookInventory;
import com.satish.entity.BookRating;

public interface BookService {
	public List<Book> getBooks(String author, String category);
	public BookInfo getBookInfo(Integer bookId);
	public void updateBookRating(BookRating bookRating);
	public void updateBookInventory(BookInventory bookInventory);
}
