package com.dxc.bookstoreapi.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dxc.bookstoreapi.model.Book;

public interface BookstoreRepository extends JpaRepository<Book, Long> {
	
	List<Book> findByTitle(String title);
	
	@Query(value = "SELECT * FROM Book WHERE id IN "
			+ "(SELECT author_id FROM Author WHERE name = :name AND birthday = :birthday)", nativeQuery = true)
	List<Book> findByAuthor(String name, String birthday);
	
	Optional<Book> findByIsbn(String isbn);
	
	@Transactional
	void deleteByIsbn(String isbn);

}
