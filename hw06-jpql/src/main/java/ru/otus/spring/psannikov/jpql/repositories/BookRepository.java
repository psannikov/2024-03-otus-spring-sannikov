package ru.otus.spring.psannikov.jpql.repositories;

import ru.otus.spring.psannikov.jpql.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Optional<Book> findById(long id);

    List<Book> findAll();

    Book save(Book book);

    void deleteById(long id);
}
