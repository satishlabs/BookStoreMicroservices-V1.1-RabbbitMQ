package com.booksearch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booksearch.entity.Book;

public interface BookDAO extends JpaRepository<Book, Integer>{
	public List<Book> getBooksByAuthorAndCategory(String author,String category);
	public List<Book> getBooksByAuthor(String author);
	public List<Book> getBooksByCategory(String category);
}
