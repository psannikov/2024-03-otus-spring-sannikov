package ru.otus.spring.psannikov.batch.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.psannikov.batch.models.postgres.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    Book findBookById(Long id);

    List<Book> findAll();
}