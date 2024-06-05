package ru.otus.spring.psannikov.data.nosql.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.psannikov.data.nosql.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Book> findById(String id);

    List<Book> findAll();

    Book save(Book book);

    void deleteById(String id);
}
