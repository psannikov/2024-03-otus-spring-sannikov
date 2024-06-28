package ru.otus.spring.psannikov.data.nosql.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.psannikov.data.nosql.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {

    Optional<Author> findById(String id);

    Optional<Author> findByFullName(String fullName);

    List<Author> findAll();
}
