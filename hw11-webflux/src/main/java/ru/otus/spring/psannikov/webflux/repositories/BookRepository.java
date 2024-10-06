package ru.otus.spring.psannikov.webflux.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.psannikov.webflux.models.Book;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
