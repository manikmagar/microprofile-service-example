package com.javastreets.ee.microprofile.entities;

public class Book {

	private String isbn;
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Person getAuthor() {
		return author;
	}
	public void setAuthor(Person author) {
		this.author = author;
	}
	private String title;
	private String description;
	private Person author;
	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", description=" + description + ", author=" + author + "]";
	}
	
}
