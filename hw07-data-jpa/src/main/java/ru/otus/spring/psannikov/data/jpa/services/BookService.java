package ru.otus.spring.psannikov.data.jpa.services;

import ru.otus.spring.psannikov.data.jpa.models.Book;
import ru.otus.spring.psannikov.data.jpa.models.Comment;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> findById(long id);

    List<Book> findAll();

    Book insert(String title, long authorId, long genreId);

    Book update(long id, String title, long authorId, long genreId);

    void deleteById(long id);
}
