package com.dxc.bookstoreapi.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "isbn", unique = true)
	private String isbn;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@ElementCollection
	@CollectionTable(name="author",	joinColumns = @JoinColumn(name="AUTHOR_ID"))
	private List<Author> authors;
	
	@Column(name = "year")
	private int year;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "genre")
	private String genre;
}
