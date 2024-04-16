package ru.otus.spring.psannikov.domain;

public class Book {

	private final String title;

	public Book(String name) {
		this.title = name;
	}

	public String getTitle() {
		return title;
	}
}
