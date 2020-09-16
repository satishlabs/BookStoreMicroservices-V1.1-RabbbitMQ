package com.bookprice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookprice.entity.BookPrice;

public interface BookPriceDAO extends JpaRepository<BookPrice, Integer>{

}
