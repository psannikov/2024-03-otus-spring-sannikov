package ru.otus.spring.psannikov.jdbc.repositories;

import ru.otus.spring.psannikov.jdbc.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    List<Author> findAll();

    Optional<Author> findById(long id);
}
