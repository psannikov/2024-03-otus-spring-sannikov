package ru.otus.spring.psannikov.mvc.view.services;

import ru.otus.spring.psannikov.mvc.view.models.Book;
import ru.otus.spring.psannikov.mvc.view.models.Comment;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> findById(long id);

    List<Book> findAll();

    Book insert(String title, long authorId, long genreId);

    Book update(long id, String title, long authorId, long genreId, List<Comment> comments);

    void deleteById(long id);
}
