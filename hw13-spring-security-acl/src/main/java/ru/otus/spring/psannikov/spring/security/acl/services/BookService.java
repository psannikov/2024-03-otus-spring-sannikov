package ru.otus.spring.psannikov.spring.security.acl.services;

import ru.otus.spring.psannikov.spring.security.acl.models.Book;
import ru.otus.spring.psannikov.spring.security.acl.models.Comment;

import java.util.List;

public interface BookService {
    Book findById(long id);

    List<Book> findAll();

    Book insert(String title, long authorId, long genreId);

    Book update(long id, String title, long authorId, long genreId, List<Comment> comments);

    void deleteById(long id);
}
