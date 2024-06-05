package ru.otus.spring.psannikov.data.nosql.services;

import ru.otus.spring.psannikov.data.nosql.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(String id);

    List<Author> findAll();

    void deleteById(String id);
}
