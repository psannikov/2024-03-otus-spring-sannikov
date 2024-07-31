package ru.otus.spring.psannikov.data.rest.services;

import ru.otus.spring.psannikov.data.rest.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(long id);

    List<Author> findAll();
}
