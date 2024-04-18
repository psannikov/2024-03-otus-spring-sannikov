package ru.otus.spring.psannikov.dao;

import ru.otus.demo.domain.Book;

public interface BookDao {

	Book findByTitle(String title);
}
