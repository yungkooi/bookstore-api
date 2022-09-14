package com.dxc.bookstoreapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dxc.bookstoreapi.exception.ResourceNotFoundException;
import com.dxc.bookstoreapi.model.Book;
import com.dxc.bookstoreapi.repository.BookstoreRepository;
import com.dxc.bookstoreapi.service.BookstoreService;

@Service
public class BookstoreServiceImpl implements BookstoreService {

	@Autowired
	private BookstoreRepository bookstoreRepository;

	@Override
	public Book saveBook(Book book) {
		return bookstoreRepository.save(book);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookstoreRepository.findAll();
	}
	
	@Override
	public List<Book> getBooksByTitle(String title) {
		
		List<Book> books = bookstoreRepository.findByTitle(title);
		
		if (!CollectionUtils.isEmpty(books)) {
			return books;
		}
		
		throw new ResourceNotFoundException("Book", "title", title);
	}

	@Override
	public void deleteBook(String isbn) {
		
		bookstoreRepository.findByIsbn(isbn).orElseThrow(() -> new ResourceNotFoundException("Book", "isbn", isbn));
		bookstoreRepository.deleteByIsbn(isbn);
	}

	@Override
	public List<Book> getBooksByAuthor(String name, String birthday) {

		List<Book> books = bookstoreRepository.findByAuthor(name, birthday); 
		
		if (!CollectionUtils.isEmpty(books)) {
			return books;
		}
		
		throw new ResourceNotFoundException("Book", "author", String.format("name=%s and birthday=%s", name, birthday));

	}

	@Override
	public Book updateBook(Book book, String isbn) {
		Book existingBook = bookstoreRepository.findByIsbn(isbn).orElseThrow(() -> new ResourceNotFoundException("Book", "isbn", isbn));
		
		existingBook.setAuthors(book.getAuthors());
		existingBook.setGenre(book.getGenre());
		existingBook.setPrice(book.getPrice());
		existingBook.setTitle(book.getTitle());
		existingBook.setYear(book.getYear());
		
		bookstoreRepository.save(existingBook);
		
		return existingBook;
	}

}
