package com.dxc.bookstoreapi.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Author {

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "birthday", nullable = false)
	private String birthday;
	
}
