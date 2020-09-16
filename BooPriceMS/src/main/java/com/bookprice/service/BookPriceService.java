package com.bookprice.service;

import com.bookprice.entity.BookPrice;

public interface BookPriceService {
	public BookPrice getBookPriceById(Integer bookId);

	public double getOfferedPriceById(Integer bookId);
}
