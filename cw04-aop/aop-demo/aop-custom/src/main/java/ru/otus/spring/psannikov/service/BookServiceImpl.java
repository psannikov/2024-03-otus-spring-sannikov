package ru.otus.spring.psannikov.service;

import ru.otus.demo.dao.BookDao;
import ru.otus.demo.domain.Book;

public class BookServiceImpl implements BookService {
	private final BookDao bookDao;

	public BookServiceImpl(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public Book getByTitle(String title) {
		return bookDao.findByTitle(title);
	}
}
