package com.satish.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.satish.dao.BookDAO;
import com.satish.dao.BookInventoryDAO;
import com.satish.dao.BookRatingDAO;
import com.satish.dto.BookInfo;
import com.satish.dto.BookPriceInfo;
import com.satish.entity.Book;
import com.satish.entity.BookInventory;
import com.satish.entity.BookRating;

@Service
@Transactional
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookDAO bookDAO;
	
	@Autowired
	private BookRatingDAO bookRatingDAO;

	@Autowired
	private BookInventoryDAO bookInventoryDAO;
	
	@Override
	public List<Book> getBooks(String author, String category) {
		List<Book> mybooks = new ArrayList<Book>();
		if(author.equals("All Authors") && category.equals("All Categories")) {
			mybooks = bookDAO.findAll();
		}else if(author.equals("All Authors") && !category.equals("All Categories")) {
			mybooks = bookDAO.getBooksByCategory(category);
		}else if(!author.equals("All Authors") && category.equals("All Categories")) {
			mybooks = bookDAO.getBooksByAuthor(author);
		}else {
			mybooks = bookDAO.getBooksByAuthorAndCategory(author, category);
		}
		return mybooks;
	}

	@Override
	public BookInfo getBookInfo(Integer bookId) {
		BookInfo bookInfo = new BookInfo();
		
		//1. Book Details
		Book book = bookDAO.findById(bookId).get(); 
		bookInfo.setBookId(book.getBookId());
		bookInfo.setBookName(book.getBookName());
		bookInfo.setAuthor(book.getAuthor());
		bookInfo.setPublications(book.getPublications());
		bookInfo.setCategory(book.getCategory());
		
		//2. Book Rating Details
		BookRating bookRating = bookRatingDAO.findById(bookId).get();
		bookInfo.setAvgRating(bookRating.getAvgRating());
		bookInfo.setNumberOfSearches(bookRating.getNumberOfSearches());
		
		//3.Book Inventory Details
		BookInventory bookInventory = bookInventoryDAO.findById(bookId).get();
		bookInfo.setBooksAvailable(bookInventory.getBooksAvailable());
		
		
		//4. Book Price Details
		RestTemplate bookPriceRest = new RestTemplate();
		String endpoints = "http://localhost:9000/bookPrice/"+bookId;
		BookPriceInfo bpInfo = bookPriceRest.getForObject(endpoints, BookPriceInfo.class);
		
		bookInfo.setPrice(bpInfo.getPrice());
		bookInfo.setOffer(bpInfo.getOffer());
		
		return bookInfo;
	}

	@Override
	public void updateBookRating(BookRating bookRating) {
		bookRatingDAO.save(bookRating);
	}

	@Override
	public void updateBookInventory(BookInventory bookInventory) {
		bookInventoryDAO.save(bookInventory);
	}

}
