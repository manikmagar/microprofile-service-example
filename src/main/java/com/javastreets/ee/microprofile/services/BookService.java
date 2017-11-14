package com.javastreets.ee.microprofile.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import com.javastreets.ee.microprofile.entities.Book;
import com.javastreets.ee.microprofile.entities.Person;

@ApplicationScoped
public class BookService {
	
	List<Book> books;
	
	@PostConstruct
	private void init() {
		Book book = new Book();
		book.setIsbn("1");
		book.setTitle("Eclipse MicroProfile");
		book.setDescription("Getting started with Eclipse MicroProfile");
		book.setAuthor(new Person(1, "Micro", "Eclipse"));
		
		books = new ArrayList<Book>();
		books.add(book);
	}

	public List<Book> listAll(){
		
			
		return books;
	}
	
	public Book getBook(String isbn) {
		List<Book> isbnList = books.stream().filter(book -> book.getIsbn().equals(isbn)).collect(Collectors.toList());
		if(isbnList.isEmpty()) return null;
		return isbnList.get(0);
	}
	
	public boolean addBook(Book book) {
		books.add(book);
		return true;
	}
	
}
