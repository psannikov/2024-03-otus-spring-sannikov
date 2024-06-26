package ru.otus.spring.psannikov.data.jpa.services;

import ru.otus.spring.psannikov.data.jpa.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(long id);

    List<Author> findAll();
}
