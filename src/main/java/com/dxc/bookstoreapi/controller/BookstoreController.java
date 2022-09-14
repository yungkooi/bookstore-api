package com.dxc.bookstoreapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.bookstoreapi.model.Book;
import com.dxc.bookstoreapi.service.BookstoreService;

@RestController
@RequestMapping("/api/bookstore")
public class BookstoreController {

	@Autowired
	private BookstoreService bookstoreService;

	// http://localhost:8080/api/bookstore
	@GetMapping
	public List<Book> getAllBooks() {
		return bookstoreService.getAllBooks();
	}

	// http://localhost:8080/api/bookstore/add
	@PostMapping("/add")
	public ResponseEntity<Book> saveBooks(@RequestBody Book book){
		return new ResponseEntity<Book>(bookstoreService.saveBook(book), HttpStatus.CREATED);
	}

	// http://localhost:8080/api/bookstore/author?title=Hello World
	@GetMapping("/title")
	public ResponseEntity<List<Book>> getBooksByTitle(@RequestParam String title) {
		return new ResponseEntity<List<Book>>(bookstoreService.getBooksByTitle(title), HttpStatus.OK);
	}

	// http://localhost:8080/api/bookstore/author?name=author1&birthday=01-01-01
	@GetMapping("/author")
	public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam String name, String birthday) {
		return new ResponseEntity<List<Book>>(bookstoreService.getBooksByAuthor(name, birthday), HttpStatus.OK);
	}

	// http://localhost:8080/api/bookstore/1111-2222-3333
	@PutMapping("{isbn}")
	public ResponseEntity<Book> updateBook(@PathVariable("isbn") String isbn, @RequestBody Book book) {
		return new ResponseEntity<Book>(bookstoreService.updateBook(book, isbn), HttpStatus.OK);
	}

	// http://localhost:8080/api/bookstore/1111-2222-3333
	@DeleteMapping("{isbn}")
	public ResponseEntity<String> deleteBook(@PathVariable("isbn") String isbn) {
		bookstoreService.deleteBook(isbn);
		return new ResponseEntity<String>("Book deleted sucessfully", HttpStatus.OK);
	}

}
