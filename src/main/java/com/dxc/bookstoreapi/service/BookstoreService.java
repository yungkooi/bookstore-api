package com.dxc.bookstoreapi.service;

import java.util.List;

import com.dxc.bookstoreapi.model.Book;

public interface BookstoreService {

	Book saveBook(Book book);
	
	List<Book> getAllBooks();
	
	List<Book> getBooksByTitle(String title);
	
	List<Book> getBooksByAuthor(String authorName, String authorBirthday);
	
	Book updateBook(Book book, String isbn);
	
	void deleteBook(String isbn);
}
