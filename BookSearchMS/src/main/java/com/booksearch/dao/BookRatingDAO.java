package com.booksearch.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booksearch.entity.BookRating;

public interface BookRatingDAO extends JpaRepository<BookRating, Integer>{
	
}
