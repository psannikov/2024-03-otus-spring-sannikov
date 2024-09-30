package ru.otus.spring.psannikov.docker.services;

import ru.otus.spring.psannikov.docker.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(long id);

    List<Author> findAll();
}
