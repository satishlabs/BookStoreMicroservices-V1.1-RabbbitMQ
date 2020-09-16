package com.booksearch.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booksearch.entity.BookInventory;

public interface BookInventoryDAO extends JpaRepository<BookInventory, Integer>{

}
